package com.example.your_trainer;

import java.util.List;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetTimeAdapter extends BaseAdapter implements OnClickListener

{

	private final Context context;
	private final List<AExercisContent> listOfExer;
	int selectedPosition = 0;
	private int changedPos = 0;
	private  boolean isLongTouch = false;


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

    	EditText edTime = (EditText)convertView.findViewById(R.id.etTime);
    	Button btnPlus = (Button)convertView.findViewById(R.id.btnPlus);
    	Button btnMinus = (Button)convertView.findViewById(R.id.btnMinus);



    	edTime.setText( Integer.toString( exerCont.getTime() ));

    	Integer pos = new Integer(position);

    	btnPlus.setTag(pos);
        btnMinus.setTag(pos);
        edTime.setTag(pos);


        btnPlus.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View pView, MotionEvent pEvent)
			{

				if( isLongTouch == false )
				{
					return false;
				}

				pView.onTouchEvent(pEvent);
	               // We're only interested in when the button is released.
	               if (pEvent.getAction() == MotionEvent.ACTION_UP)
	               {

	            	   Toast.makeText(context , "Finger up", Toast.LENGTH_LONG).show();

	               }

	               else if (pEvent.getAction() == MotionEvent.ACTION_DOWN)
	               {
	            	   Toast.makeText(context , "Finger down", Toast.LENGTH_LONG).show();

	               }

	               isLongTouch = false;

				return false;
			}
		});


        btnPlus.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View view)
			{

				isLongTouch = true;
                 /*
				Integer posObj = (Integer) view.getTag();
    			int posit = posObj.intValue();
    			int newTime = listOfExer.get(posit).getTime() + 10;
    			listOfExer.get(posit).setTime(newTime);
    			notifyDataSetChanged();
    			*/

				return false;
			}
		});


        btnPlus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view)
			{
				if( isLongTouch == true )
				{
					return ;
				}

				Integer posObj = (Integer) view.getTag();
    			int posit = posObj.intValue();
    			int newTime = listOfExer.get(posit).getTime() + 1;
    			listOfExer.get(posit).setTime(newTime);
    			notifyDataSetChanged();

			}
		});

        btnMinus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view)
			{
				Integer posObj = (Integer) view.getTag();
    			int posit = posObj.intValue();
    			int newTime = listOfExer.get(posit).getTime() - 1;
    			listOfExer.get(posit).setTime(newTime);
    			notifyDataSetChanged();

			}
		});

        edTime.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View view, int keyCode, KeyEvent event)
			{

				if (keyCode == event.KEYCODE_ENTER )
				{
					//Toast.makeText(context , "Enter touced ", Toast.LENGTH_LONG).show();

					EditText edTex = (EditText)view;
					String newTime = edTex.getText().toString();
					int nTime = Integer.parseInt(newTime);

					Integer posObj = (Integer) view.getTag();
	    			int posit = posObj.intValue();

	    			listOfExer.get(posit).setTime(nTime);
	    			notifyDataSetChanged();



				}

				if (keyCode == event.KEYCODE_BACKSLASH )
				{
					//EditText edTex = (EditText)view;
					//String newTime = "0";
					//int nTime = Integer.parseInt(newTime);

					Integer posObj = (Integer) view.getTag();
	    			int posit = posObj.intValue();

	    			listOfExer.get(posit).setTime(0);
	    			notifyDataSetChanged();

				}

				/*
				int pressedKey =  event.getUnicodeChar();

				EditText edTex = (EditText)view;
				String newTime = edTex.getText().toString();
				int nTime = Integer.parseInt(newTime);

				Integer posObj = (Integer) view.getTag();
    			int posit = posObj.intValue();
    			//int newTime = listOfExer.get(posit).getTime() - 1;

    			listOfExer.get(posit).setTime(nTime);
    			notifyDataSetChanged();
    			*/

				return false;
			}
		});



/*
        edTime.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				int newTime =  Integer.parseInt(s.toString());
				listOfExer.get(changedPos).setTime(newTime);


			}
		});

*/



        edTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view)
			{
				Integer obj = (Integer) view.getTag();
				changedPos = obj.intValue();

			}
		});




       // btnPlus.setFocusableInTouchMode(false);
      //  btnPlus.setFocusable(false);




/*
        String[] nums = new String[20];

        for (int i = 0; i < nums.length ; i++)
        	nums[i] = Integer.toString(i);

	     nums[0] = Integer.toString(exerCont.getTime());
    	NumberPicker numPick = (NumberPicker)convertView.findViewById(R.id.npTime);
    	numPick.setMinValue(0);
    	numPick.setMaxValue(20);
    	numPick.setWrapSelectorWheel(false);
    	numPick.setDisplayedValues(nums);
    	numPick.setValue(0);
*/

    	/*
    	NumberPicker numPick = (NumberPicker)convertView.findViewById(R.id.npTime);
    	Integer pos = new Integer(position);
    	numPick.setTag(pos);

    	numPick.setOnValueChangedListener(new OnValueChangeListener(){

    		@Override
    		public void onValueChange(NumberPicker picker, int oldVal, int newVal)
    		{

    			Integer posObj = (Integer) picker.getTag();
    			int posit = posObj.intValue();
    			//AExercisContent exerCont = listOfExer.get(posit);
    			//exerCont.setTime(newVal);
    			listOfExer.get(posit).setTime(newVal);
    			Toast.makeText(context , "Index of list:" + String.valueOf(posit), Toast.LENGTH_LONG).show();

    		}

    	});

*/
    	//OnValueChangedListener onValueChangedListener = new OnValueChangedListener();

    	//numPick.setOnValueChangedListener(onValueChangedListener);

    	//EditText etTime =  (EditText) convertView.findViewById(R.id.etTime);


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


	//@Override
	//public void onClick(View view)
	{
/*
		switch(view.getId())
		{
			case R.id.btnPlus :
				Integer posObj = (Integer) view.getTag();
    			int posit = posObj.intValue();
    			int newTime = listOfExer.get(posit).getTime() + 1;
    			listOfExer.get(posit).setTime(newTime);
    			notifyDataSetChanged();

			break;

			case R.id.btnMinus:
				Integer posObj1 = (Integer) view.getTag();
    			int posit1 = posObj1.intValue();
    			int newTime1 = listOfExer.get(posit1).getTime() - 1;
    			listOfExer.get(posit1).setTime(newTime1);
    			notifyDataSetChanged();

				;
			break;

			case R.id.etTime:
				//Integer obj = (Integer) view.getTag();
				//changedPos = obj.intValue();
				break;

			default :
				;
		}
*/
		// selectedPosition = (Integer)view.getTag();
        // notifyDataSetChanged();

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}



}
