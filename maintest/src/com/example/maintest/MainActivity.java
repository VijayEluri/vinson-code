package com.example.maintest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
/*
 * @author octobershiner
 * 2011 07 27
 * SE.HIT
 * һ����ʾandroid���ٶȸ�Ӧ��������
 * */

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    //����LOG��ǩ
    private static final String TAG = "sensor";
    private  SensorManager sm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //����һ��SensorManager����ȡϵͳ�Ĵ���������
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //ѡȡ���ٶȸ�Ӧ��
        int sensorType = Sensor.TYPE_ACCELEROMETER;
        int sensorTM = Sensor.TYPE_MAGNETIC_FIELD;
        
        /*
         * ��õ�һ������ ע���¼�
         * ����1 ��SensorEventListener������
         * ����2 ��Sensor һ����������ж��Sensorʵ�֣��˴�����getDefaultSensor��ȡĬ�ϵ�Sensor
         * ����3 ��ģʽ ��ѡ���ݱ仯��ˢ��Ƶ��
         * */
        sm.registerListener(myAccelerometerListener,sm.getDefaultSensor(sensorType),SensorManager.SENSOR_DELAY_NORMAL);
        //sm.registerListener(myAccelerometerListenerM,sm.getDefaultSensor(sensorTM),SensorManager.SENSOR_DELAY_NORMAL);
        
    }
    
    /*
     * SensorEventListener�ӿڵ�ʵ�֣���Ҫʵ����������
     * ����1 onSensorChanged �����ݱ仯��ʱ�򱻴�������
     * ����2 onAccuracyChanged ��������ݵľ��ȷ����仯��ʱ�򱻵��ã�����ͻȻ�޷��������ʱ
     * */
    final SensorEventListener myAccelerometerListener = new SensorEventListener(){
        
        //��дonSensorChanged����
        public void onSensorChanged(SensorEvent sensorEvent){
            if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                Log.i(TAG,"onSensorChanged");
                
                //ͼ�����Ѿ���������ֵ�ĺ���
                float X_lateral = sensorEvent.values[0];
                float Y_longitudinal = sensorEvent.values[1];
                float Z_vertical = sensorEvent.values[2];
                Log.i(TAG,"\n heading "+X_lateral);
                Log.i(TAG,"\n pitch "+Y_longitudinal);
                Log.i(TAG,"\n roll "+Z_vertical);
                
                TextView TV = (TextView) findViewById(R.id.TV);
                
                TV.setText(String.format("X:%f\nY:%f\nZ:%f",X_lateral,Y_longitudinal,Z_vertical));
            }
        }
        //��дonAccuracyChanged����
        public void onAccuracyChanged(Sensor sensor , int accuracy){
            Log.i(TAG, "onAccuracyChanged");
        }
    };
    

    
    public void onPause(){
        /*
         * �ܹؼ��Ĳ��֣�ע�⣬˵���ĵ����ᵽ����ʹactivity���ɼ���ʱ�򣬸�Ӧ����Ȼ������Ĺ��������Ե�ʱ����Է��֣�û��������ˢ��Ƶ��
         * Ҳ��ǳ��ߣ�����һ��Ҫ��onPause�����йرմ����������򽲺ķ��û������������ܲ�����
         * */
        sm.unregisterListener(myAccelerometerListener);
        super.onPause();
    }
    
}
