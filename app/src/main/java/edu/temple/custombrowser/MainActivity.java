package edu.temple.custombrowser;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*...........................................................................................................................................*/
/*Global Variables*/
    ArrayList<WebPage> fragment_array = new ArrayList<>();
    ViewPager viewPager;
    EditText userText;
    Button button;
    String user_url;

    PagerAdapter pagerAdapter;

    int NUM_FRAGS = 1;
    int newPageIndex = 0;
    int i;

    /*...........................................................................................................................................*/
/*Web Browser Main Activity*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(5);
        userText = (EditText) findViewById(R.id.edit_url);
        button = (Button) findViewById(R.id.go_button);

        final Uri data = getIntent().getData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_url = userText.getText().toString();
                i = viewPager.getCurrentItem();
                fragment_array.get(i);

                if (data != null) {
                    user_url = data.toString();
                }

                fragment_array.get(i).goToPage(user_url);

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }

        });

    }
/*...........................................................................................................................................*/
/*Pager Adapter Class*/

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            fragment_array.add(newPageIndex, new WebPage());
            newPageIndex++;
            return fragment_array.get(pos);
        }

        @Override
        public int getCount() {
            return NUM_FRAGS;
        }
    }

/*...........................................................................................................................................*/
/* Options Menu*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_previous) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        }
        else if(item.getItemId() == R.id.menu_new) {
            WebPage fragment = new WebPage();
            fragment_array.add(newPageIndex, fragment);
            NUM_FRAGS++;
            pagerAdapter = viewPager.getAdapter();
            pagerAdapter.notifyDataSetChanged();
            viewPager.setCurrentItem(newPageIndex);
            userText.setText("");

        }
        else if(item.getItemId() == R.id.menu_next) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

        }
        return super.onOptionsItemSelected(item);
    }
}

