package com.datealive.controller;

import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.AdRateDto;
import com.datealive.entity.pojo.Ad;
import com.datealive.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: AdController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/5  15:46
 */
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping("/getAdList")
    public PageResult getAllAdMapperByPage(@RequestParam(defaultValue = "1") Integer currentPage){
        return adService.getAllAdMapperByPage(currentPage);
    }


    @GetMapping("/getAdByAdId/{AdId}")
    public Result getAdByAdId(@PathVariable("AdId")Long AdId){
        Ad adByAdId = adService.getAdByAdId(AdId);
        if(adByAdId!=null){
            return Result.success("根据广告ID:"+AdId+"获取成功",adByAdId);
        }else{
            return Result.error("根据广告ID"+AdId+"获取失败");
        }
    }

    @GetMapping("/getAdByRateId/{RateId}")
    public Result getAdByRateId(@PathVariable("RateId")Long RateId){
        List<Ad> adByRateId = adService.getAdByRateId(RateId);
        if(adByRateId!=null){
            return Result.success("根据比赛ID:"+RateId+"获取成功",adByRateId);
        }else{
            return Result.error("根据比赛ID"+RateId+"获取失败");
        }
    }


    @PostMapping("/insertAd")
    public Result insertAd(@RequestBody AdRateDto adRateDto){
        boolean insertAd = adService.insertAd(adRateDto);
        if(insertAd){
            return Result.success("广告插入成功");
        }else {
            return Result.error("广告插入失败");
        }
    }

    @DeleteMapping("/deleteAdByAdId/{adId}")
    public Result deleteAdByAdId(@PathVariable("adId") Long ad_id){
        if (adService.deleteAdByAdId(ad_id)) {
            return Result.success("根据广告ID"+ad_id+"成功删除广告");
        }else{
            return Result.error("根据广告ID"+ad_id+"删除广告失败");
        }
    }

    @DeleteMapping("/deleteAdByRateId/{rateId}")
    public Result deleteAdByRateId(@PathVariable("rateId") Long rate_id){
        if (adService.deleteAdByRateId(rate_id)) {
            return Result.success("根据比赛ID"+rate_id+"成功删除广告");
        }else{
            return Result.error("根据比赛ID"+rate_id+"删除广告失败");
        }
    }

    @PutMapping("/updateAdByAdId")
    public Result updateAdByAdId(@RequestBody Ad ad){
        if (adService.updateAdByAdId(ad)) {
            return Result.success("修改广告成功");
        }else{
            return Result.error("修改广告失败");
        }
    }
























}
