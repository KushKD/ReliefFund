package cmreliefdund.kushkumardhawan.com.relieffund.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;


import cmreliefdund.kushkumardhawan.com.relieffund.Modal.History_POJO;
import cmreliefdund.kushkumardhawan.com.relieffund.R;

public class HistoryAdapter extends ArrayAdapter<History_POJO> {

    private Context context;
    private List<History_POJO> userlist;

    public HistoryAdapter(Context context, int resource, List<History_POJO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.userlist = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_inbox, parent, false);
        // Rates_Pojo rates_object = rates.get(position);
        History_POJO RO = userlist.get(position);
        TextView tv1 = (TextView) view.findViewById(R.id.itemone);
        TextView tv2 = (TextView) view.findViewById(R.id.itemtwo);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);



        tv1.setText(RO.getAmount());
        tv2.setText(RO.getPayment_date_time());
        imageView1.setImageResource(R.drawable.donate);


        return view;
    }
}
