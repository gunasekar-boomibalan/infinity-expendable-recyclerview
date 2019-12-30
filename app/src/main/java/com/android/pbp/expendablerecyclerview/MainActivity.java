package com.android.pbp.expendablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Element> listItem = new ArrayList<>();
    private ArrayList<Element> baseListItem= new ArrayList<>();
    private static final String ROOT_ID= "A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        loadListData();
        loadList();
    }

    /*----------------------------------------------------------------------------------------------
    The Recycler view need list of element type data to render the infinity Expendable Layout.
     here json file is used to create the list of element type.
     we can create the element by creating element class setter & Constructor
    ----------------------------------------------------------------------------------------------*/

    private void loadListData() {
        String element;
        try {
            element = AssetJSONFile("elements.json", this);
            Type listType = new TypeToken<ArrayList<Element>>() {}.getType();
            baseListItem = new Gson().fromJson(element,listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Element e : baseListItem) {
            if (e.getParentId().equalsIgnoreCase(ROOT_ID)) {
                listItem.add(e);
            }
        }

    }

    public static String AssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }


    private void loadList() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listItem, baseListItem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
