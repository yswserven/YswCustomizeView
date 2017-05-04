package com.yswcustomizeview.mActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yswcustomizeview.R;
import com.yswcustomizeview.mMode.MyCityMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyCityActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_getdata;
    private Button bt_insertdata;
    private TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initUi();
    }

    private void initUi() {
        bt_getdata = (Button) findViewById(R.id.bt_getdata);
        bt_insertdata = (Button) findViewById(R.id.bt_insertdata);
        tv_data = (TextView) findViewById(R.id.tv_data);
        bt_getdata.setOnClickListener(this);
        bt_insertdata.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_getdata:
                List<MyCityMode> jsonData = getJsonData();
                tv_data.setText("jsonData.size = " + jsonData.size());
                break;
            case R.id.bt_insertdata:
                break;
            default:
                break;
        }
    }

    public List<MyCityMode> getJsonData() {
        StringBuilder buffer = new StringBuilder();
        List<MyCityMode> cityList = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(getAssets().open("address.json"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            bufferedReader.close();
            reader.close();

            JSONObject root = new JSONObject(buffer.toString());
            JSONArray array = root.getJSONArray("RECORDS");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                MyCityMode mode = new MyCityMode();
                mode.setId(object.getInt("id"));
                mode.setAddres_name(object.getString("addres_name"));
                mode.setJd_code(object.getString("jd_code"));
                mode.setPid(object.getInt("pid"));
                mode.setLevel(object.getInt("level"));
                cityList.add(mode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityList;
    }
}
