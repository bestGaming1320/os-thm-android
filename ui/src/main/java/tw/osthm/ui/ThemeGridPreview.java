package tw.osthm.ui;

import android.annotation.SuppressLint;
import android.content.*;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.*;
import android.widget.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class ThemeGridPreview extends BaseAdapter {
    ArrayList<HashMap<String, Object>> list;
    LayoutInflater inflater;
    private LinearLayout linear_base;
    private LinearLayout linear_title;
    private ImageView imageview_back;
    private ImageView imageview_fab;
    private TextView textview_title;
    private TextView textview_name;

    public ThemeGridPreview(Context mContext, ArrayList<HashMap<String, Object>> list) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HashMap<String, Object> getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = inflater.inflate(R.layout.theme_preview, null);
        linear_base = view.findViewById(R.id.linear_base);
        linear_title = view.findViewById(R.id.linear_title);
        imageview_back = view.findViewById(R.id.imageview_back);
        imageview_fab = view.findViewById(R.id.imageview_fab);
        textview_name = view.findViewById(R.id.textview_name);
        textview_title = view.findViewById(R.id.textview_title);

        textview_name.setText(list.get(i).get("themesname").toString());
        ArrayList<HashMap<String, Object>> thmarray = new Gson().fromJson(list.get(i).get("themesjson").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>() {
        }.getType());
        textview_name.setTextColor(Color.parseColor(thmarray.get(0).get("colorBackgroundText").toString()));
        textview_title.setTextColor(Color.parseColor(thmarray.get(0).get("colorPrimaryText").toString()));
        linear_base.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(thmarray.get(0).get("colorBackground").toString())));
        linear_title.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(thmarray.get(0).get("colorPrimary").toString())));
        imageview_fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(thmarray.get(0).get("colorButton").toString())));
        imageview_fab.setColorFilter(Color.parseColor(thmarray.get(0).get("colorButtonText").toString()));
        imageview_back.setColorFilter(Color.parseColor(thmarray.get(0).get("colorPrimaryImage").toString()));
        return view;
    }
}