package com.dong.vmqpay.controller;

import com.dong.vmqpay.pojo.BaseResponse;
import com.dong.vmqpay.pojo.res.CreateOrderRes;
import com.dong.vmqpay.service.WebService;
import com.dong.vmqpay.utils.ResUtils;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/pay")
public class WebController {
    @Autowired
    private WebService webService;
    private static Logger logger = Logger.getLogger("WebController.class");


    @GetMapping("/test")
    public BaseResponse test() {
        return ResUtils.success(new String("111"));
    }

    @RequestMapping("/createOrder")
    public String createOrder(double price) {
        BaseResponse baseResponse = webService.createOrder(price);
        CreateOrderRes res = ((CreateOrderRes) baseResponse.getData());
        return "<script>window.location.href = '/paypage/pay.html?orderNo=" + res.getOrderNo() + "'</script>";
    }

    @RequestMapping("/getOrder")
    public BaseResponse getOrder(String orderNo){
        logger.info("getOrder");
        return webService.getOrder(orderNo);
    }

    @RequestMapping("/checkOrder")
    public BaseResponse checkOrder(String orderNo) {
        if (orderNo == null) {
            return ResUtils.fail("请传入订单编号");
        }
        return webService.checkOrder(orderNo);

    }


    @RequestMapping("/enQrcode")
    public void enQrcode(HttpServletResponse resp, String url) throws IOException {
        if (url != null && !"".equals(url)) {
            ServletOutputStream stream = null;
            try {
                int width = 200;//图片的宽度
                int height = 200;//高度
                stream = resp.getOutputStream();
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
                MatrixToImageWriter.writeToStream(m, "png", stream);
            } catch (Exception e) {
//                logger.warning(e.toString());
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
    }

    @RequestMapping("/deQrcode")
    public BaseResponse deQrcode(String base64) {
//        logger.info("deQrcode");
//        logger.info(String.valueOf(base64==null));
        if (base64 != null && !"".equals(base64)) {
            try {
                MultiFormatReader multiFormatReader = new MultiFormatReader();
                byte[] bytes1 = Base64.getDecoder().decode(base64);
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
                BufferedImage image = ImageIO.read(bais);
                //定义二维码参数
                Map hints = new HashMap();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                //获取读取二维码结果
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                Result result = multiFormatReader.decode(binaryBitmap, hints);
                //stream.print(result.getText());
                return ResUtils.success(result.getText());
            } catch (Exception e) {
//                logger.warning(e.toString());
                e.printStackTrace();
            }
        }
        return ResUtils.fail();
    }

    @RequestMapping("/deQrcode2")
    public BaseResponse deQrcode2(@RequestParam("file") MultipartFile file) {
        if (file != null) {
            try {
                MultiFormatReader multiFormatReader = new MultiFormatReader();
                byte[] bytes1 = file.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
                BufferedImage image = ImageIO.read(bais);
                //定义二维码参数
                Map hints = new HashMap();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                //获取读取二维码结果
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                Result result = multiFormatReader.decode(binaryBitmap, hints);

                //stream.print(result.getText());
                System.out.println(result.getText());
                return ResUtils.success(result.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResUtils.fail();
    }

}
