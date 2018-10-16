package io.renren.modules.job.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wxstc.dl.mapper.*;
import com.wxstc.dl.util.*;
import io.renren.modules.roat.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Task {
    @Autowired
    private TyphoonHackMapper typhoonHackMapper;
    @Autowired
    private TyphoonMapper typhoonMapper;
    @Autowired
    private TyphoonInfoMapper typhoonInfoMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private TyMessageMapper tyMessageMapper;
    @Autowired
    private ArticleMapper articleMapper;

//    @Scheduled(initialDelay = 3000)
    public void task1() throws Exception {
        getTyphoonInfo();
        cityInfo();
        getTyphoonNews();
    }

    @Transactional
    public void getTyphoonInfo(){
        LinkedHashSet<Integer> ids = new LinkedHashSet<Integer>();
        int beginYear = 1949;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        int endYear = Integer.parseInt(sf.format(new Date()));
        for (;beginYear<=endYear;beginYear++){
            String res = HttpClientUtil.doGet("http://typhoon.nmc.cn/weatherservice/typhoon/jsons/list_"+beginYear+"?t=1536545272605&callback=mycallback");
            //解析jsonp
            String res2 = res.substring(res.lastIndexOf("({")+1, res.length()-1);
            LinkedHashMap linkedHashMap = JsonUtils.jsonToPojo(res2, LinkedHashMap.class);
            Object t1 = linkedHashMap.get("typhoonList");
            if(t1!=null){
                ArrayList<ArrayList> typhoonList = (ArrayList) t1;
                for (ArrayList row2:
                        typhoonList) {
                    int tid = Integer.valueOf(row2.get(0).toString());
                    ids.add(tid);
                }
            }
        }
        List<Typhoon> typhoons = typhoonMapper.selectList(new EntityWrapper<Typhoon>());
        for (Typhoon t:
             typhoons) {
            ids.remove(t.tid);
        }
        for (Integer tid:
             ids) {
            String tinfo = HttpClientUtil.doGet("http://typhoon.nmc.cn/weatherservice/typhoon/jsons/view_"+tid);
            String tinfo2 = tinfo.substring(tinfo.lastIndexOf("({")+1, tinfo.length()-1);
            LinkedHashMap tyinfo = JsonUtils.jsonToPojo(tinfo2, LinkedHashMap.class);
            Object tyinfo1 = tyinfo.get("typhoon");
            if(tyinfo1!=null){
                ArrayList taiinfo = (ArrayList) tyinfo1;
                MyArrayList row = new MyArrayList(taiinfo);
                //int tid = Integer.valueOf(row.getOrElse(0).toString());
                String tname = row.getOrElse(1);
                String tnamecn = row.getOrElse(2);
                String tmp1 = row.getOrElse(4);
                String tmp2 = row.getOrElse(5);
                String jianjie = row.getOrElse(6);
                String status = row.getOrElse(7);
                Typhoon ty = new Typhoon(tid,tname,tnamecn,jianjie,status);
                typhoonMapper.insert(ty);
                //获取台风详细信息
                ArrayList<ArrayList> taiinfos = (ArrayList) taiinfo.get(8);
                for (ArrayList rowt:
                        taiinfos) {
                    MyArrayList taifenginfo = new MyArrayList(rowt);
                    String infoid = taifenginfo.getOrElse(0);
                    String infotime = taifenginfo.getOrElse(1);
                    String qiangdu = taifenginfo.getOrElse(3);
                    String lat = taifenginfo.getOrElse(4);
                    String lng = taifenginfo.getOrElse(5);
                    String midqiya = taifenginfo.getOrElse(6);
                    String maxsudu = taifenginfo.getOrElse(7);
                    String fangxiang = taifenginfo.getOrElse(8);
                    String yidongsudu = taifenginfo.getOrElse(9);
                    TyphoonInfo info = new TyphoonInfo(ty.tid,infoid,infotime,qiangdu,lat,lng,midqiya,maxsudu,fangxiang,yidongsudu);
                    typhoonInfoMapper.insert(info);
                    Object hack = taifenginfo.get(10);
                    if(hack!=null){
                        ArrayList<ArrayList> hack1 = (ArrayList) hack;
                        for (ArrayList hackinfot:
                                hack1) {
                            MyArrayList hackinfo = new MyArrayList(hackinfot);
                            String hackLevel = hackinfo.getOrElse(0);
                            String dongbei = hackinfo.getOrElse(1);
                            String dongnan = hackinfo.getOrElse(2);
                            String xinan = hackinfo.getOrElse(3);
                            String xibei = hackinfo.getOrElse(4);
                            String pinfoid = hackinfo.getOrElse(5);
                            TyphoonHack typhoonHack = new TyphoonHack(hackLevel,dongbei,dongnan,xinan,xibei,pinfoid);
                            typhoonHackMapper.insert(typhoonHack);
                        }
                    }
                }
            }
        }
    }

    @Transactional
    public void cityInfo(){
        String s = HttpClientUtil.doGet("http://gitee.com/dl_ontheway/Demo/raw/master/city.txt");
        final List<City> cities = JsonUtils.jsonToList(s, City.class);
        for (City c:
             cities) {
            cityMapper.insert(c);
        }
    }

    @Transactional
    public void getTyphoonNews() throws Exception {
        String s = HttpClientUtil.doGet("http://typhoon.weather.com.cn/index.shtml");
        Document doc = Jsoup.parse(s);
        Element company_info = doc.select("div.blockLB").get(0);
        Elements elements = company_info.select("li");
        int a = 0;
        for (int i=elements.size()-1;i>=0;i--) {
            if(a<8){
                elements.remove(i);
            }else {
                Element e = elements.get(i);
                List<Node> nodes = e.childNodes();
                String year = "";
                for (Node n:
                     nodes) {
                    if(n.toString().indexOf("年")>0){
                        year = n.toString().split("年")[0];
                    }else if(n.hasAttr("href")){
                        String typhoonName = n.childNode(0).toString();
                        String url = n.attr("href");
                        String typid = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
                        Article art = new Article(year,typhoonName,url,typid);
                        articleMapper.insert(art);
                        for (int j = 1; j < 50; j++) {
                            String tyinfo = HttpClientUtil.doGet("http://www.weather.com.cn/zt/tqzt/"+typid+"/"+j+".htm");
                            if(!tyinfo.contains("({"))
                                break;
                            String tinfo2 = tyinfo.substring(tyinfo.lastIndexOf("({")+1, tyinfo.lastIndexOf(")"));
                            LinkedHashMap map = JSON.parseObject(tinfo2, LinkedHashMap.class);
                            JSONArray infos = (JSONArray) map.get("d");
                            for (int k = 0; k < infos.size(); k++) {
                                JSONObject m = (JSONObject) infos.get(k);
                                //String c2 = m.get("c2").toString();
                                String content = m.get("c4").toString();
                                content = content.replaceAll("<a.*>(.*)<\\/a>","");
                                content = content.replaceAll("<A.*>(.*)<\\/A>","");
                                String title = m.get("c1").toString();
                                title = title.replaceAll("<a.*>(.*)<\\/a>","");
                                title = title.replaceAll("<A.*>(.*)<\\/A>","");
                                if(!content.contains("结束")||!title.contains("结束")){
                                    String time = m.get("c3").toString();
                                    String video = m.get("c8").toString();
                                    String images = "";
                                    images += m.getOrDefault("c9","").toString()+",";
                                    images += m.getOrDefault("c10","").toString()+",";
                                    images += m.getOrDefault("c11","").toString()+",";
                                    images += m.getOrDefault("c12","").toString()+",";
                                    images += m.getOrDefault("c13","").toString()+",";
                                    images += m.getOrDefault("c14","").toString()+",";
                                    TyMessage message = new TyMessage(content,title,time,video,images,typid);
                                    tyMessageMapper.insert(message);
                                }
                            }
                        }
                    }
                }
            }
            a++;
        }
//        Iterator<Element> iterator = elements.iterator();
//        while (iterator.hasNext()){
//            Element next = iterator.next();
//        }
        System.out.println(1123);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String tyinfo = HttpClientUtil.doGet("http://www.weather.com.cn/zt/tqzt/"+2550137+"/50.htm");

//        String str = "å±±ç«¹";
//
//        byte[] bytes = str.getBytes();
//        bytes = str.getBytes("ISO-8859-1");
//
//        String str2 = new String(bytes);
//        str2 = new String(bytes,"UTF-8");
//        str2 = EncodeUtil.toUtf8(str);
        System.out.println(tyinfo);
    }
}
