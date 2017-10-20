package edu.upc.eseiaat.pma.shoppinglist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {
    // declaracion de botones y lista
    private ArrayList<String> item_list;
    private ArrayAdapter<String> adapter;

    private ListView list;
    private  Button btn_add;
    private  EditText edit_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);


         list = (ListView) findViewById(R.id.list);
         edit_item = (EditText) findViewById(R.id.edit_item);
         btn_add = (Button) findViewById(R.id.btn_add);

        item_list = new ArrayList<>();
        item_list.add("Papas");
        item_list.add("holaa");
        item_list.add("sdsd");
        item_list.add("cwbas");


        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                item_list
        );

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItem();


            }
        });



        edit_item.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                addItem();

                return true;
            }
        });



        list.setAdapter(adapter);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                maybeRemoveItem(position);



                return true;
            }
        });

    }

    private void maybeRemoveItem(final int pos) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setTitle(R.string.confirm);
        String fnt = getResources().getString(R.string.confirm_sms);
        builder.setMessage(
                String.format(fnt,
                        item_list.get(pos)
                        ));

        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item_list.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton(android.R.string.cancel,null);
        builder.create().show();




    }

    private void addItem() {
        String item_text = edit_item.getText().toString();
        if(!item_text.isEmpty()){

            item_list.add(item_text);
            adapter.notifyDataSetChanged();}

            edit_item.setText("");
        }
    }

