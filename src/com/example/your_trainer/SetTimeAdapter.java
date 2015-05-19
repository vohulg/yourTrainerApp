package com.example.your_trainer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class SetTimeAdapter extends BaseAdapter implements OnClickListener

{

	private final Context context;
	private final List<AExercisContent> listOfExer;
	int selectedPosition = 0;

	public SetTimeAdapter (Context context, List<AExercisContent> listOfExer)
	{
		this.context = context;
		this.listOfExer = listOfExer;

	}

	@Override
	public int getCount() {

		return listOfExer.size();
	}

	@Override
	public Object getItem(int position) {

		return listOfExer.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)

	{
		AExercisContent exerCont = listOfExer.get(position);

		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_exer_setting, null);

		}

		TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
    	tvName.setText(exerCont.getName());

    	EditText etTime =  (EditText) convertView.findViewById(R.id.etTime);


/*
    	boolean check = (position == selectedPosition);
    	rbChoose.setChecked(check);

    	rbChoose.setOnClickListener(this);
    	rbChoose.setTag(position);

    	rbChoose.setFocusableInTouchMode(false);
    	rbChoose.setFocusable(false);

    	*/




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

}
