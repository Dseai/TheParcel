package com.syn.theparcel.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.syn.theparcel.DB.HistoryDataBase;
import com.syn.theparcel.R;
import com.syn.theparcel.enty.Express;
import com.syn.theparcel.enty.GetCompany;
import com.syn.theparcel.listview.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.design.R.styleable.TextInputLayout;

/**
 * Created by 孙亚楠 on 2016/11/21.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_id;
    private AutoCompleteTextView edittext_name;
    private TextView textView;
    private TextInputLayout textInputLayout_id;

    private ListView listView;

    private HistoryDataBase myDataBase;
    private LayoutInflater layoutInflater;
    private ArrayList<Express> arrayList;
    //int id;

    ImageView imageView;
    Map map;
    List list;
    Intent news_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
    }

    public void init(){

        textView = (TextView)findViewById(R.id.text);
        editText_id = (EditText)findViewById(R.id.edittext_id);
        edittext_name = (AutoCompleteTextView)findViewById(R.id.edittext_name);
        textInputLayout_id=(TextInputLayout)findViewById(R.id.textinputlayout_id);
        Button button_query=(Button)findViewById(R.id.button_query);
        button_query.setOnClickListener(this);
        imageView = (ImageView)findViewById(R.id.image_main);




        layoutInflater= getLayoutInflater();

        myDataBase=new HistoryDataBase(this);
        arrayList=myDataBase.getArray();
        listView=(ListView)findViewById(R.id.listView1);
        final HistoryAdapter historyAdapter=new HistoryAdapter(layoutInflater,arrayList);
        listView.setAdapter(historyAdapter);
        /**
         * 点击条目查询
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText_id.setText(arrayList.get(i).getExpressNumber());
                edittext_name.setText(arrayList.get(i).getExpressName());
            }
        });
        /**
         * 长按可删除
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(MainActivity.this ).setTitle("删除").setMessage("确定删除这条记录吗？")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myDataBase.deleteNote(arrayList.get(position).getId());
                        arrayList=myDataBase.getArray();
                        HistoryAdapter historyAdapter1=new HistoryAdapter(layoutInflater,arrayList);
                        listView.setAdapter(historyAdapter1);
                    }
                }).create().show();
                return true;
            }
        });



        news_intent = new Intent(MainActivity.this,NewsActivity.class);

        editText_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //检测错误输入，当输入错误时，hint会变成红色并提醒
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //检查实际是否匹配，由自己实现
                if (checkType(charSequence.toString())) {
                    textInputLayout_id.setErrorEnabled(true);
                   // textInputLayout_id.setError("请检查格式");
                    return;
                } else {
                    textInputLayout_id.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getcompany();

        ArrayAdapter arrayAdapter;//输入提示
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        edittext_name.setAdapter(arrayAdapter);
    }


    public boolean checkType(String check){
        for(int i=0;i<check.length();i++){
            if(check.charAt(i)>'9'||check.charAt(i)<'0')return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String string_editext_name = edittext_name.getText().toString();
        String string_edittext_id= editText_id.getText().toString();
        Express express = (Express) map.get(string_editext_name);
        /**
         * 插入到数据库
         */
        Express express1=new Express(string_edittext_id,string_editext_name);
      myDataBase.toInsert(express1);
        arrayList=myDataBase.getArray();
        HistoryAdapter historyAdapter1=new HistoryAdapter(layoutInflater,arrayList);
        listView.setAdapter(historyAdapter1);

        if(express!=null){

            news_intent.putExtra("logo",express.getLogo());
            news_intent.putExtra("name",express.getExpressName());
            news_intent.putExtra("num", string_edittext_id);
            startActivity(news_intent);

        }
        else Toast.makeText(MainActivity.this,"查询不到此快递公司",Toast.LENGTH_SHORT).show();

    }


    public void getcompany(){

        GetCompany getCompany=new GetCompany(MainActivity.this,R.raw.company);
        list =getCompany.getList();
        map=getCompany.getMap();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
