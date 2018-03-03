package com.xiangchi.controller.crossborder;

import com.umf.crossborder.interfaceTest.main.MainTest;
import com.umf.crossborder.interfaceTest.utils.RestIdGenerator;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ******************  类说明  *********************
 *
 * @author :  fanxiangchi@umftech.com
 * @version :  1.0
 * @class :  com.xiangchi.controller.crossborder.ExchangeApplyController
 * @since :  3/1/18 8:37 AM
 * ***********************************************
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @RequestMapping(value = "/receipt_file_upload", method = RequestMethod.POST)
    // ResponseBody
    public Map<String, String> apply(@RequestBody Map<String, Object> parametersMap){

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("responseCode", "0000");

        System.out.println(parametersMap);

        Map<String, Object> cbReqMap = new HashMap<>();
        Map<String, String> mm = new HashMap<>();

        cbReqMap.put("notify_url","www.www.com");
        cbReqMap.put("batch", mm);
        mm.put("file_name", "RFXS_3861_20170717_USD_170717R11.txt");
        mm.put("currency", "USD");
        mm.put("batch_date", "20170717");
        mm.put("batch_no", "170717R11");
        mm.put("file_path", "/");
        try {
            String retMsg = MainTest.httpPost(cbReqMap, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches");
            responseMap.put("responseMsg", retMsg);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("responseCode", e.getMessage());
        }


        return responseMap;
    }

    @RequestMapping(value = "/confirm_remittance", method = RequestMethod.POST)
    public Map<String, String> confirm_remittance(@RequestBody Map<String, Object> parametersMap) throws Exception{
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("responseCode", "0000");

//		String id = "RB_GE3TANZRG5JDCML4GIYDCNZQG4YTOMRQGE3TCMJQG6VA";
        String batchNo = "18012301";
        String batchDate = "20180123";
        String prefix = "RB_";
        String merDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String id  = RestIdGenerator.generate(
                String.format("%s|%s", batchNo, batchDate),
                merDate,
                prefix);
        Map<String, Object> mm = new HashMap<String, Object>();
        mm.put("currency", "USD");
        mm.put("total", 693.47);


        Map<String, Object> mm2 = new HashMap<String, Object>();
        mm2.put("currency", "USD");
        mm2.put("total", 0.0);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("receipt_rows", 2);
        m.put("receipt_amount", mm);
        m.put("receipt_fee", mm2);

        try {
            String retMsg =  MainTest.httpPost(m, "3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches/"+id+"/instruction");
            responseMap.put("responseMsg", retMsg);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("responseMsg", e.getMessage());
            responseMap.put("responseCode", "9999");
        }
        return responseMap;
    }

    @RequestMapping(value = "/remittance_query/{batchNo}/{batchDate}/{orderId}", method = RequestMethod.GET)
    public Map<String, String> remittance_query(@PathVariable String batchNo, @PathVariable String batchDate, @PathVariable String orderId) throws Exception{
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("responseCode", "0000");

        // String batchNo = "18012301";		//批次号
        // String batchDate = "20180123";		//批次日期
        String prefix = "RB_";				//批次ID前缀
        //批次ID
        String merDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String id  = RestIdGenerator.generate(
                String.format("%s|%s",batchNo, batchDate),
                merDate,
                prefix);
        String paymentNo = "587b5a8a20ed540444f60081";

        try {
            String retMsg =  MainTest.httpGet("3861", "http://fx.soopay.net/cbeweb_rest/receipt/batches/"+id+"/payment/"+paymentNo);
            responseMap.put("responseMsg", retMsg);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("responseMsg", e.getMessage());
            responseMap.put("responseCode", "9999");
        }
        return responseMap;
    }
}

