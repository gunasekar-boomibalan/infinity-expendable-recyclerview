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
            if (e.getParentId().equalsIgnoreCase(Element.NO_PARENT)) {
                listItem.add(e);
            }
        }
    }

/*    private void loadListData(){
        Element e1 = new Element("A1","Level 1", Element.NO_PARENT, 0,true, true);
        Element e2 = new Element("A2", "Level 2", "A1", 1,true, true);
        Element e3 = new Element("A3","Level 3",  "A2", 2,true, true);
        Element e4 = new Element("A4","Level 4",  "A3", 3,true, true);
        Element e5 = new Element("A5","Level 5", "A4", 4,true, true);
        Element e6 = new Element("A6", "Level 6", "A5", 5,true, false);


        Element j1 = new Element("J1","Level 1", Element.NO_PARENT, 0,true, true);
        Element j2 = new Element("J2", "Level 2", "J1", 1,true, true);
        Element j3 = new Element("J3","Level 3",  "j2", 2,true, true);
        Element j4 = new Element("J4","Level 4",  "J3", 3,true, true);
        Element j5 = new Element("J5","Level 5", "J4", 4,true, true);
        Element j6 = new Element("J6", "Level 6", "J5", 5,true, false);

        baseListItem.add(e1);
        baseListItem.add(e2);
        baseListItem.add(e3);
        baseListItem.add(e4);
        baseListItem.add(e5);
        baseListItem.add(e6);

        baseListItem.add(j1);
        baseListItem.add(j2);
        baseListItem.add(j3);
        baseListItem.add(j4);
        baseListItem.add(j5);
        baseListItem.add(j6);

        for (Element e : baseListItem) {
            if (e.getParentId().equalsIgnoreCase(Element.NO_PARENT)) {
                listItem.add(e);
            }
        }
    }*/

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
