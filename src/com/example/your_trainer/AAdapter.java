package com.example.your_trainer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


public class AAdapter extends BaseAdapter implements OnClickListener
{

	private final Context context;
	private final List<AComplexContainer> listComplex;
	int selectedPosition = 0;

	public AAdapter (Context context, List<AComplexContainer> listComplex)
	{
		this.context = context;
		this. listComplex = listComplex;

	}

	@Override
	public int getCount() {

		return listComplex.size();
	}

	@Override
	public Object getItem(int position) {

		return  listComplex.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)

	{
		AComplexContainer complCont = listComplex.get(position);

		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.itemcomplex, null);

		}

		TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
    	tvName.setText(complCont.getName());

    	RadioButton rbChoose =  (RadioButton) convertView.findViewById(R.id.rbChoose);


    	boolean check = (position == selectedPosition);
    	rbChoose.setChecked(check);

    	rbChoose.setOnClickListener(this);
    	rbChoose.setTag(position);

    	rbChoose.setFocusableInTouchMode(false);
    	rbChoose.setFocusable(false);




    	return convertView;
	}

	public int getSelectedItem()
	{
		return selectedPosition;
	}


	@Override
	public void onClick(View view) {
		 selectedPosition = (Integer)view.getTag();
         notifyDataSetChanged();

	}

	public int getSelectedPos()
	{
		return selectedPosition;

	}

	public void setSelectedPos( int selPos)
	{
		 selectedPosition = selPos;
		 notifyDataSetChanged();

	}

}
