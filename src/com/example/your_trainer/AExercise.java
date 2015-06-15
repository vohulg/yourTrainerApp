package com.example.your_trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class AExercise extends Activity implements OnClickListener //, TextToSpeech.OnInitListener
{

	private final String appDir = "train";

	// for connect of database
	final String LOG_TAG = "vh_tag";
	DBHelper dbhelper;
	SQLiteDatabase dataBase;

	private final int endTimer = 0;

	private String choosedComplexName = null;
	private String choosedComplexId = null;
	private String choosedComplexFolder = null;

	private int currentIndex = 0;
	private int countOfExersInComplex = 0;
	private int pausedIndex = -1;


	// widgets
	 private WebView wbPictur;
	// private TextView tvDescr;
	 private TextView tvExerName;
	 private TextView tvTimer;
	 private LinearLayout llMain;
	 private RelativeLayout rlLayout;


	 // values for timer
	private CountDownTimer timer;
	private boolean isPaused = false;
	private long restMillisUntilFinished = 0;

	 private static final int SecVal = 30;
	 private static final int MillisInSec = 1000;

	 // value for exercises content
	 private List<AExercisContent> listOfExer;

	 // speech text
	 private TextToSpeech tts;

	 // mediapleer
	 private MediaPlayer mplayer;
	 AudioManager audioManager;


	 @Override
	 protected void onStop()
	 {
		 if (timer != null)
		   {
			   timer.cancel();

		   }

		 super.onPause();

	 }



	 @Override
	 protected void onCreate(Bundle savedInstanceState)
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.aexercise);

	        Intent intent = getIntent();
	        choosedComplexName = intent.getStringExtra("nameChoosedCompex");
	    	choosedComplexId = intent.getStringExtra("nameChoosedId");
	    	choosedComplexFolder = intent.getStringExtra("nameChoosedFolder");




	    	wbPictur = (WebView)findViewById(R.id.wvImgShow);
	    	 tvTimer = (TextView)findViewById(R.id.tvTimer);
	    	// tvDescr = (TextView)findViewById(R.id.tvDescr);
	    	 tvExerName = (TextView)findViewById(R.id.tvExerName);
	    	 rlLayout = (RelativeLayout)findViewById(R.id.rlLayout);
	    	 rlLayout.setOnClickListener(this);
	    	// llMain = (LinearLayout)findViewById(R.id.llMain);

	    	// llMain.setOnClickListener(this);

	    	 initialTTS();
		     initialPlayer();







	 }

	 public List<AExercisContent> getListOfExer(String ComplexId)
	 {

		choosedComplexId = ComplexId;
		fill_List();

		 return listOfExer;


	 }

	 private boolean fill_List()
	 {

		 dbhelper = new DBHelper(this, "dbTrainer");
	     dataBase = dbhelper.getReadableDatabase();
	     listOfExer = new ArrayList<AExercisContent>();

	    String column[] = {"exes_name", "exes_descr", "exes_photourl", "exes_timeinsec", "exes_order", "exes_istabata" };
		String selection = "exes_complid =" + choosedComplexId;
		String orderby = "exes_order";

			Cursor c =  dataBase.query("tbExercises", column, selection, null, null, null, orderby);
			int rows = c.getCount();

			if (rows == 0)
			{
				Log.d(LOG_TAG, "0 rows");
				return false;
			}

			else
			{
				countOfExersInComplex = rows;

			}

			int ExerName = c.getColumnIndex("exes_name");
			int  ExerDescr = c.getColumnIndex("exes_descr");
			int  ExerPhotoUrl = c.getColumnIndex("exes_photourl");
			int  ExerTime = c.getColumnIndex("exes_timeinsec");

			// fill list of complexes
			c.moveToFirst();
			for (int i =0; i < rows; i++)
			{

				String name =  c.getString(ExerName );
				String url = c.getString(ExerPhotoUrl);
				String descr = c.getString(ExerDescr);
				String time = c.getString(ExerTime);

				AExercisContent obj = new AExercisContent(name, url, descr, Integer.parseInt(time), choosedComplexFolder );
				listOfExer.add(obj);
				c.moveToNext();

			}

			c.close();
		   dbhelper.close();
		   dataBase.close();
			return true;
	}

	 @Override
	 public void onClick(View view)
	 {
		 if (timer != null)
			 timer.cancel() ;

		 isPaused = true;
		 pausedIndex = currentIndex;


    	Intent intent = new Intent(this, APaused.class) ;
    	intent.putExtra("currentExerIndex", currentIndex);
    	intent.putExtra("maxCountOfExer", countOfExersInComplex);
    	startActivityForResult(intent, 13);





	 }



	private void showTimer(int indexOfList)
		{


		// 1. get count of time, description, url, name of exercises

		AExercisContent instan = listOfExer.get(indexOfList);

		// get path to folder of complex
		String pathComplex = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + appDir + "/" + instan.getFolderPath();

		String gifPath =  "file://" +  pathComplex + "/" + (indexOfList + 1) + ".jpg";

		String html = "<html><head></head><body><img src=\""+ gifPath + "\"></body></html>";
		//mWebView.loadData(html, "text/html","utf-8");




		int count = instan.getTime();
		//String gifPath = instan.getUrl();
		//String gifPath = "file:///android_asset/" + choosedComplexId + "/" + (indexOfList + 1) + ".gif";
		String descr = instan.getDescr();
		String name = instan.getName();

		// 2. show values in widget
		 wbPictur.loadUrl(gifPath);
		 //tvDescr.setText(descr);
    	 tvExerName.setText(name);

    	 speechOut(name);


		// 3. Show timer
		count = count *  MillisInSec;

		   if (timer != null)
		   {
			   timer.cancel();

		   }


		   if (isPaused == true)
			{
				count = (int)restMillisUntilFinished;
			}


			timer = new CountDownTimer(count, MillisInSec) {

				@Override
				public void onTick(long millisUntilFinished)
				{
					restMillisUntilFinished = millisUntilFinished;
					String res = Integer.toString((int)(millisUntilFinished / MillisInSec));
					tvTimer.setText(res );

				}

				@Override
				public void onFinish()
				{
					playAudio();

					isPaused = false;
					restMillisUntilFinished = 0;

					currentIndex ++;

					// finish of complex
					if (currentIndex > (countOfExersInComplex-1))
					{

						 if (timer != null)
						   {
							   timer.cancel();

						   }
						Intent intentEnd = new Intent(getApplicationContext(), AEnding.class); // call activity
						startActivity(intentEnd);
						//wbPictur.setVisibility(View.INVISIBLE);
						return;
					}
					// continue complex
					else
					   showTimer(currentIndex);


				}
			};

			timer.start();



		}


	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

		// if not pressed buttun in APaused activity
		if (resultCode != -1)
		{
			isPaused = true;
			showTimer(currentIndex);
			return;

		}

		currentIndex = data.getIntExtra("choosedIndex", resultCode);

		if(pausedIndex == currentIndex)
			isPaused = true;

		else
			isPaused = false;

		showTimer(currentIndex);


    }




	@SuppressWarnings("deprecation")
	void speechOut(String txt)
	{
		//txt = "hello";
		tts.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
		//String utteranceId = null;
		//tts.speak(txt, TextToSpeech.QUEUE_FLUSH,null, utteranceId);


	}

	private void initialTTS()
	{

		 //TextToSpeech.OnInitListener listner = new TextToSpeech.OnInitListener();
   	 tts = new TextToSpeech(this,  new TextToSpeech.OnInitListener() {

			@Override
			public void onInit(int status)
			{
				if (status == TextToSpeech.SUCCESS)
				{
					Locale local = new Locale("ru");
					int ret = tts.setLanguage(Locale.getDefault());

					if (ret == TextToSpeech.LANG_MISSING_DATA || ret == TextToSpeech.LANG_NOT_SUPPORTED)
							{

						      Toast  toast = Toast.makeText(getApplicationContext(), R.string.warn, Toast.LENGTH_SHORT);
					           toast.show();

							}

					else
					{

					 fill_List(); // получаем из базы список упражнений
			    	 showTimer(0); // запуск таймера

					}

				}

				else
				{
					 Toast  toast = Toast.makeText(getApplicationContext(), R.string.tts_error, Toast.LENGTH_SHORT);
			           toast.show();
				}


			}

   	 } );




	}


	private void initialPlayer()
	{
		audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);


	}

	private void playAudio()
	{

		mplayer = MediaPlayer.create(getApplicationContext(), R.raw.ring);
		mplayer.start();


	}

	 @Override
	 protected void onDestroy()
	 {

		 releaseMplayer();
		 releaseTTS();

		 super.onDestroy();
	 }

	 private void releaseMplayer()
	 {
		 if(mplayer != null)
		 {
			try {
			 mplayer.release();
			 mplayer = null;
			}

			catch (Exception e)
			{
				e.printStackTrace();
			}

		 }

	 }

	 private void releaseTTS()
	 {
		 if (tts != null)
		 {
			 tts.stop();
			 tts.shutdown();
		 }

	 }



}


