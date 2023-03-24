package com.my.lottery.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.lottery.entity.KuaiLeBa;
import com.my.lottery.mapper.KuaiLeBaMapper;
import com.my.lottery.utils.HttpUtil;
import com.my.lottery.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * OKR旧数据导入
 */
@Slf4j
@Service
public class KuaiLeBaService {

    @Autowired
    private KuaiLeBaMapper kuaiLeBaMapper;

    public void init() throws Exception{
        String s = HttpUtil.sendGetRequest("http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=kl8&issueCount=&issueStart=&issueEnd=&dayStart=&dayEnd=&pageNo=1&pageSize=3000&week=&systemType=PC", null);

        Map<String, Object> map = JsonUtil.jsonToMap(s);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<LinkedHashMap> result = (ArrayList<LinkedHashMap>) map.get("result");

        kuaiLeBaMapper.delete(null);

        for (LinkedHashMap data : result) {
            String code = data.get("code").toString();
            String date = data.get("date").toString();
            String red = data.get("red").toString();

            KuaiLeBa kuaiLeBa = new KuaiLeBa();
            kuaiLeBa.setCode(code);
            kuaiLeBa.setDate(sdf.parse(date.substring(0, 10)));
            kuaiLeBa.setWeek(date.substring(11, 12));
            kuaiLeBa.setRed(red);
            kuaiLeBaMapper.insert(kuaiLeBa);
            System.out.println(code + "  " + date + "  " + red);
        }
    }

    public Map<String, Object> punchCard() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<String> col = new ArrayList<>();
        List<Integer[]> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            KuaiLeBa last = ex(data, i, (i + 1));
            col.add(sdf.format(last.getDate()));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("col", col);

        return result;
    }

    public KuaiLeBa ex(List<Integer[]> result, Integer col, Integer limit) {
        List<KuaiLeBa> data = kuaiLeBaMapper.selectList(new QueryWrapper<KuaiLeBa>().last(" order by code desc limit  " + limit));
        List<Integer> all = new ArrayList<>();
        for (KuaiLeBa kuaiLeBa : data) {
            String red = kuaiLeBa.getRed();
            String[] split = red.split(",");
            for (String s : split) {
                all.add(Integer.valueOf(s));
            }
        }

        for (int i = 1; i <= 80; i++) {
            Integer[] d = new Integer[3];
            d[0] = (i - 1);
            d[1] = col;
            d[2] = Collections.frequency(all, i);
            result.add(d);
        }
        return data.get(data.size() - 1);
    }


    public static void main(String[] args) {
        String s = "2023-03-21(二)";
        System.out.println(s.substring(0, 10));
        System.out.println(s.substring(11, 12));


    }

}
