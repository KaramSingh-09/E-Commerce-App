package com.allandroidprojects.ecomsample.product;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.allandroidprojects.ecomsample.R;
import com.allandroidprojects.ecomsample.fragments.ImageListFragment;
import com.allandroidprojects.ecomsample.fragments.ViewPagerActivity;
import com.allandroidprojects.ecomsample.notification.NotificationCountSetClass;
import com.allandroidprojects.ecomsample.options.CartListActivity;
import com.allandroidprojects.ecomsample.startup.Item;
import com.allandroidprojects.ecomsample.startup.MainActivity;
import com.allandroidprojects.ecomsample.startup.SearchProduct;
import com.allandroidprojects.ecomsample.startup.Word;
import com.allandroidprojects.ecomsample.utility.ImageUrlUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ItemDetailsActivity extends AppCompatActivity {
    int imagePosition;
    String stringImageUri;

    public static final String STRING_IMAGE_URI = "ImageUri";
    public static final String STRING_IMAGE_POSITION = "ImagePosition";

    private String name;
    private String price;
    private String desc;
    private Word word;

    SearchProduct products = new SearchProduct();
    List<Item> productitems;

    ImageView itemImage;
    TextView itemName;
    TextView itemDesc;
    TextView itemPrice;

    int productPosition;

    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        SimpleDraweeView mImageView = (SimpleDraweeView)findViewById(R.id.image1);
        TextView textViewAddToCart = (TextView)findViewById(R.id.text_action_bottom1);
        TextView textViewBuyNow = (TextView)findViewById(R.id.text_action_bottom2);
        TextView product_names = (TextView) findViewById(R.id.item_detail_name);
        TextView product_price = (TextView) findViewById(R.id.item_detail_price);

        LinearLayout Desc_Layout = (LinearLayout) findViewById(R.id.text_layout);
        LinearLayout apriori_layout = (LinearLayout) findViewById(R.id.apriori);

        //Getting image uri from previous screen
        if (getIntent() != null) {
            stringImageUri = getIntent().getStringExtra(ImageListFragment.STRING_IMAGE_URI);
            imagePosition = getIntent().getIntExtra(ImageListFragment.STRING_IMAGE_POSITION, 0);
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            desc = getIntent().getStringExtra("desc");
            Boolean flag = getIntent().getBooleanExtra("flag", false);
             productPosition = getIntent().getIntExtra("position", -1);

            boolean suggest = false;

             if(name.equals("Red Party Dress")) {
                 position = 10;
                 suggest = true;

             }

             else  if(name.equals("Spring Outdoor cloth"))
             {
                 position = 9;
                 suggest = true;
             }


             else if(name.equals("Sofa pillows"))
             {
                 position = 12;
                 suggest = true;
             }

             else if(name.contentEquals("Double White Bed"))
             {
                 position = 14;
                 suggest = true;
             }

             else if(name.contentEquals("Desktop Computer"))
             {
                 position = 7;
                 suggest = true;
             }

             else if(name.contentEquals("Samsung Phone"))
             {
                 position = 6;
                 suggest = true;
             }

             else
                 position = 0;




            if(flag & suggest)
            {
                Desc_Layout.setVisibility(View.GONE);
            }


            else {

                apriori_layout.setVisibility(View.GONE);

            }

            word = new Word(name, desc, price);


            productitems = products.getProductList();

            itemImage = (ImageView) findViewById(R.id.search_image);
            itemName = (TextView) findViewById(R.id.search_name);
            itemDesc = (TextView) findViewById(R.id.description_part);
            itemPrice = (TextView) findViewById(R.id.search_price);




            itemImage.setImageResource(productitems.get(position).getItemImage());
            itemName.setText(productitems.get(position).getItemName());
            itemDesc.setText(productitems.get(position).getItemDesc());
            itemPrice.setText("rupee"+ productitems.get(position).getItemPrice());

        }

        product_names.setText(name);
        product_price.setText(price);

        final boolean flagg = false;

        apriori_layout.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view)
            {
                Intent intent = getIntent();
                intent.putExtra(STRING_IMAGE_URI, productitems.get(position).getItemImageUrl());
                intent.putExtra(STRING_IMAGE_POSITION, position);
                intent.putExtra("name", productitems.get(position).getItemName());
                intent.putExtra("price", productitems.get(position).getItemPrice());
                intent.putExtra("desc", productitems.get(position).getItemDesc());
                intent.putExtra("flag", flagg);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });



        Uri uri = Uri.parse(stringImageUri);
        mImageView.setImageURI(uri);


        textViewAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageUrlUtils imageUrlUtils = new ImageUrlUtils();
                imageUrlUtils.addCartListImageUri(stringImageUri);
                word.SetMyCard(word);

                Toast.makeText(ItemDetailsActivity.this,"Item added to cart.",Toast.LENGTH_SHORT).show();
                MainActivity.notificationCountCart++;
                NotificationCountSetClass.setNotifyCount(MainActivity.notificationCountCart);
            }
        });

        textViewBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ItemDetailsActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
