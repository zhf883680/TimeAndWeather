package com.example.timeandweather;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amap.api.services.core.AMapException;

import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;

import com.amap.api.services.weather.WeatherSearch.OnWeatherSearchListener;
import com.amap.api.services.weather.WeatherSearchQuery;



import java.util.List;
public class WeatherSearch extends com.amap.api.services.weather.WeatherSearch implements com.amap.api.services.weather.WeatherSearch.OnWeatherSearchListener{
    private Context context;
    private String city="吴中区";
    private WeatherSearchQuery mquery;
    private WeatherSearch mweathersearch;
    private LocalWeatherLive weatherlive;
    private LocalWeatherForecast weatherforecast;
    public WeatherSearch(Context context) throws AMapException {
        super(context);

    }
    /**
     * 实时天气查询
     */
    public void searchliveweather() throws AMapException {
        Log.i("thisApp","执行了查询");
        mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);//检索参数为城市和天气类型，实时天气为1、天气预报为2
        mweathersearch = new WeatherSearch(MainActivity.mContext);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }
    /**
     * 实时天气查询回调
     */
    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            Log.i("thisApp","查询成功了");
            if (weatherLiveResult != null && weatherLiveResult.getLiveResult() != null) {
                weatherlive = weatherLiveResult.getLiveResult();
                MainActivity.weatherInfo =city+"|"+weatherlive.getWeather() +"|"+weatherlive.getTemperature()+"℃|" +weatherlive.getWindDirection() + "风" + weatherlive.getWindPower() + "级|"
                        +"湿度:" + weatherlive.getHumidity() + "%";

            } else {
                MainActivity.weatherInfo="天气获取失败,2小时后重试";
            }
        } else {
            MainActivity.weatherInfo="天气获取失败,2小时后重试";
        }
    }
    @Override
    public void onWeatherForecastSearched(
            LocalWeatherForecastResult weatherForecastResult, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {

        } else {

        }
    }
}
