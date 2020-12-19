package com.example.aria_kotlin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import database.Song
import database.SongDao
import database.SongsDB
import java.util.*


class PlayActivity : AppCompatActivity() {
    var TAG: String = "Привет"
    var BtnVar1: Button? = null
    var BtnVar2: Button? = null
    var BtnVar3: Button? = null
    var BtnVar4: Button? = null
    var BtnVar5: Button? = null
    var BtnVar6: Button? = null
    var BtnVar7: Button? = null
    var BtnVar8: Button? = null
    var BtnVar9: Button? = null
    var BtnVar10: Button? = null
    var BtnPlay: ImageButton? = null
    var BtnPause: ImageButton? = null

    //var player: MediaPlayer? = null
    var rightAnswer //=1;
            : Int? = null
    var rQuestion1: Int? = null
    var rQuestion2: Int? = null
    var rQuestion3: Int? = null
    var rQuestion4: Int? = null
    var rQuestion5: Int? = null
    var rQuestion6: Int? = null
    var rQuestion7: Int? = null
    var rQuestion8: Int? = null
    var rQuestion9: Int? = null
    var rQuestion10: Int? = null
    var song1: Song? = null
    var song2: Song? = null
    var song3: Song? = null
    var song4: Song? = null
    var song5: Song? = null
    var song6: Song? = null
    var song7: Song? = null
    var song8: Song? = null
    var song9: Song? = null
    var song10: Song? = null
    var dao: SongDao? = null
    var context: Context? = null
    var mCountDownTimer: CountDownTimer? = null
    var mCountDownTimer2: CountDownTimer? = null
    var tvTimer: TextView? = null
    var tvCountAllSeconds: TextView? = null
    var tvNameRedScore: TextView? = null
    var redScoreCount: TextView? = null
    var h: Handler? = null
    var wronganswer = false
    var timeToThink = 0
    var mTimeLeftInMillis = START_TIME_IN_MILLIS
    var hero1: ImageView? = null
    var hero2: ImageView? = null
    var hero3: ImageView? = null
    var hero4: ImageView? = null
    var hero5: ImageView? = null
    var hero6: ImageView? = null
    var hero7: ImageView? = null
    var hero8: ImageView? = null
    var hero9: ImageView? = null
    var hero10: ImageView? = null
    var herominus1: ImageView? = null
    var herominus2: ImageView? = null
    var herominus3: ImageView? = null
    var herominus4: ImageView? = null
    var herominus5: ImageView? = null
    var level = 0
    var continueTimer: Long = 0
    lateinit var exoplayer: SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        initialize() //в нём UdpadeScore( потом winner()),и 4 setOnClick(selectedvariant(rightAnswer(),wrongAnswer()))
        randomQuestions()
        h = Handler()
    }

    private fun initialize() {
        BtnVar1 = findViewById<View>(R.id.button_var_1) as Button
        BtnVar2 = findViewById<View>(R.id.button_var_2) as Button
        BtnVar3 = findViewById<View>(R.id.button_var_3) as Button
        BtnVar4 = findViewById<View>(R.id.button_var_4) as Button
        BtnVar5 = findViewById<View>(R.id.button_var_5) as Button
        BtnVar6 = findViewById<View>(R.id.button_var_6) as Button
        BtnVar7 = findViewById<View>(R.id.button_var_7) as Button
        BtnVar8 = findViewById<View>(R.id.button_var_8) as Button
        BtnVar9 = findViewById<View>(R.id.button_var_9) as Button
        BtnVar10 = findViewById<View>(R.id.button_var_10) as Button
        BtnPlay = findViewById<View>(R.id.button_start_to_play) as ImageButton
        BtnPause = findViewById<View>(R.id.button_pause) as ImageButton
        tvTimer = findViewById<View>(R.id.tvTimer) as TextView
        tvCountAllSeconds = findViewById<View>(R.id.seconds_count_2) as TextView
        tvNameRedScore = findViewById<View>(R.id.tv_name_red_score) as TextView
        redScoreCount = findViewById<View>(R.id.seconds_count_red) as TextView
        hero1 = findViewById<View>(R.id.hero1) as ImageView
        hero2 = findViewById<View>(R.id.hero2) as ImageView
        hero3 = findViewById<View>(R.id.hero3) as ImageView
        hero4 = findViewById<View>(R.id.hero4) as ImageView
        hero5 = findViewById<View>(R.id.hero5) as ImageView
        hero6 = findViewById<View>(R.id.hero6) as ImageView
        hero7 = findViewById<View>(R.id.hero7) as ImageView
        hero8 = findViewById<View>(R.id.hero8) as ImageView
        hero9 = findViewById<View>(R.id.hero9) as ImageView
        hero10 = findViewById<View>(R.id.hero10) as ImageView
        herominus1 = findViewById<View>(R.id.hero_minus_1) as ImageView
        herominus2 = findViewById<View>(R.id.hero_minus_2) as ImageView
        herominus3 = findViewById<View>(R.id.hero_minus_3) as ImageView
        herominus4 = findViewById<View>(R.id.hero_minus_4) as ImageView
        herominus5 = findViewById<View>(R.id.hero_minus_5) as ImageView
        val animZoomIn = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_play)
        BtnPlay!!.startAnimation(animZoomIn)
        BtnVar1!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(1)
        }
        BtnVar2!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(2)
        }
        BtnVar3!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(3)
        }
        BtnVar4!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(4)
        }
        BtnVar5!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(5)
        }
        BtnVar6!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(6)
        }
        BtnVar7!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(7)
        }
        BtnVar8!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(8)
        }
        BtnVar9!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(9)
        }
        BtnVar10!!.setOnClickListener {
            stopPlayer()
            doPlayVisible()
            selectedVarriant(10)
        }
    }

    private fun selectedVarriant(selectedVariant: Int) {
        if (selectedVariant == rightAnswer) {
            rightAnswer()
        } else {
            wrongAnswer()
        }
    }

    private fun rightAnswer() {
//        String sec = tvTimer.getText().toString();
//        //    int seco = Integer.parseInt(sec);
//        String[] parts = sec.split(":");
//        String part1 = parts[0]; // 004
//        String part2 = parts[1]; // 034556
//        int t = Integer.parseInt(part2);
//        timeToThink = timeToThink + t;//timeToThink это int
//        tvCountAllSeconds.setText(String.valueOf(timeToThink));
        stopTimer()
        stopTimer2()
        level++
        setLevelVisibility()
        Log.d(TAG, level.toString())
        if (level >= 10) {
            try {
                //   TimeUnit.SECONDS.sleep(2);
                winner()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        randomQuestions()
    }

    private fun wrongAnswer() {
        wronganswer = true
        level--
        setLevelVisibility()
        stopTimer()
        stopTimer2()
        showTrueAnswer()
        BtnPlay!!.clearAnimation()
        BtnPlay!!.visibility = View.INVISIBLE
        BtnPause!!.visibility = View.INVISIBLE
        // doPauseVisible();
        h!!.postDelayed({ //    BtnPlay.setVisibility(View.INVISIBLE);
            doPlayVisible()
            randomQuestions()
        }, 1500)
    }

    private fun randomQuestions() {
        val arr = arrayOfNulls<Int>(121)
        for (i in arr.indices) {
            arr[i] = i + 1
        }
        arr.shuffle()
        rQuestion1 = arr[0] //чтобы не получился ноль
        rQuestion2 = arr[1]
        rQuestion3 = arr[2]
        rQuestion4 = arr[3]
        rQuestion5 = arr[4]
        rQuestion6 = arr[5]
        rQuestion7 = arr[6]
        rQuestion8 = arr[7]
        rQuestion9 = arr[8]
        rQuestion10 = arr[9]
        val random = Random()
        rightAnswer = random.nextInt(10 - 1) + 1
        nextQuestion(
            rQuestion1,
            rQuestion2,
            rQuestion3,
            rQuestion4,
            rQuestion5,
            rQuestion6,
            rQuestion7,
            rQuestion8,
            rQuestion9,
            rQuestion10,
            rightAnswer!!
        )
    }

    private fun nextQuestion(
        rQuestion1: Int?,
        rQuestion2: Int?,
        rQuestion3: Int?,
        rQuestion4: Int?,
        rQuestion5: Int?,
        rQuestion6: Int?,
        rQuestion7: Int?,
        rQuestion8: Int?,
        rQuestion9: Int?,
        rQuestion10: Int?,
        rightAnswer: Int
    ) {
        dao = SongsDB.getInstance(this)?.songDao()
        resetButtonColor()
        song1 = rQuestion1?.let { dao?.getSongById(it) }
        song2 = rQuestion2?.let { dao?.getSongById(it) }
        song3 = rQuestion3?.let { dao?.getSongById(it) }
        song4 = rQuestion4?.let { dao?.getSongById(it) }
        song5 = rQuestion5?.let { dao?.getSongById(it) }
        song6 = rQuestion6?.let { dao?.getSongById(it) }
        song7 = rQuestion7?.let { dao?.getSongById(it) }
        song8 = rQuestion8?.let { dao?.getSongById(it) }
        song9 = rQuestion9?.let { dao?.getSongById(it) }
        song10 = rQuestion10?.let { dao?.getSongById(it) }
        BtnVar1!!.setText(song1?.songName)
        BtnVar2!!.setText(song2?.songName)
        BtnVar3!!.setText(song3?.songName)
        BtnVar4!!.setText(song4?.songName)
        BtnVar5!!.setText(song5?.songName)
        BtnVar6!!.setText(song6?.songName)
        BtnVar7!!.setText(song7?.songName)
        BtnVar8!!.setText(song8?.songName)
        BtnVar9!!.setText(song9?.songName)
        BtnVar10!!.setText(song10?.songName)
        when (rightAnswer) {
            1 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song1?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    BtnPlay!!.clearAnimation()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            2 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song2?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            3 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song3?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            4 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song4?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            5 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song5?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            6 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song6?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            7 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song7?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            8 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song8?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            9 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song9?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
            10 -> {
                BtnPlay!!.setOnClickListener {
                    val s: String? = song10?.fileName
                    if (s != null) {
                        toPlay(this, s)
                    }
                    doPauseVisible()
                    startTimer(START_TIME_IN_MILLIS)
                    startTimer2()
                    BtnPlay!!.clearAnimation()
                }
                BtnPause!!.setOnClickListener {
                    pauseMusic()
                    doPlayVisible()
                }
            }
        }
    }

    //    private fun toPlay(s: String) {
//        if (player == null) {
//            player = MediaPlayer.create(this, resources.getIdentifier(s, "raw", packageName))
////            player.setOnErrorListener(MediaPlayer.OnErrorListener { mediaPlayer, i, i1 ->
////                Log.d(TAG, "i=" + i + "i1=" + i1)
////                false
////            })
//            player!!.setOnCompletionListener(OnCompletionListener { mp ->
//                mp.release()
    //             stopPlayer()
//                // if (!wronganswer) {
//                doPlayVisible()
//                // }
//                //  wronganswer = false;
//                //   player.release();
//            })
//        }
//        player!!.start() //плеер != null, поэтому эта строка здесь, а не после;
//        Log.d(TAG, "iffffff")
//    }
    private fun toPlay(context: Context?, s: String) {
        Log.d(TAG, "зашло в toPlay")
        exoplayer = SimpleExoPlayer.Builder(context!!).build()

        val dataSourceFactory =
            DefaultDataSourceFactory(context, Util.getUserAgent(context, "asdfasdf"))
        //val v:String ="raw/$s.mp3"
        val mediaSource: ProgressiveMediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri("asset:///raw/$s.mp3"))
        exoplayer.prepare(mediaSource)
        exoplayer.playWhenReady = true
        exoplayer.addListener(object : Player.EventListener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {  // The player has finished playing the media.
                    Log.d(TAG, "закончилась песня: onPlayerStateChanged")
                    stopPlayer()
                    doPlayVisible()
                }
            }
        })
    }

    private fun stopPlayer() {
        exoplayer.release() //вместо того, чтобы держать наш экземпляр медиаплеера, мы его освобождаем(release)
        false //и создаём новый, когда нажимаем play
    }

    override fun onStop() {  //когда покидаем приложение, тоже выполняется stopPlayer()
        super.onStop()
        stopPlayer()
        Log.d(TAG,"Выполнился метод онСтоп")
    }
    override fun onResume() {  //когда покидаем приложение, тоже выполняется stopPlayer()
        super.onResume()
        Log.d(TAG,"Выполнился метод онРезюм")
    }

    protected fun doPauseVisible() {
        BtnPlay!!.visibility = View.INVISIBLE
        BtnPause!!.visibility = View.VISIBLE
    }

    protected fun doPlayVisible() {
        BtnPause!!.visibility = View.INVISIBLE
        BtnPlay!!.visibility = View.VISIBLE
    }

    protected fun pauseMusic() {
        // exoplayer.pause()
        exoplayer.setPlayWhenReady(false)
        exoplayer.getPlaybackState()
    }

    private fun startTimer(mTimeLeftInMillis: Long) {
        if (mCountDownTimer != null) {                        //если таймер уже существует
            return  //то новый не создавать
        }
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val m = mTimeLeftInMillis - millisUntilFinished //60000 - 59000 = 1000
                updateOurCountDownText(m) //передаём 1
            }
            override fun onFinish() {}
        }.start()
    }

    private fun startTimer2() {
        //   if (mCountDownTimer2 != null) {                    //если таймер уже существует
        //       return;                                        //то новый не создавать
        //   }
        mCountDownTimer2 = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                val ggg = START_TIME_IN_MILLIS - millisUntilFinished
                //????
                updateOurCountDownText2(ggg) //передаём 1
            }

            override fun onFinish() {}
        }.start()
    }

    private fun updateOurCountDownText(m: Long) {
        val minutes =
            (m / 1000).toInt() / 60 // из миллисекунд, кот.остались до конца получаем минуты
        val seconds = (m / 1000).toInt() % 60 //получаем секунды, напр.2%60 =2,
        val timeLeftFormatted = String.format(Locale.getDefault(), "%2d:%02d", minutes, seconds)
        tvTimer!!.text = timeLeftFormatted
    }

    private fun updateOurCountDownText2(m: Long) {
        val minutes =
            (m / 1000).toInt() / 60 // из миллисекунд, кот.остались до конца получаем минуты
        val seconds = (m / 1000).toInt() % 60 //получаем секунды, напр.2%60 =2,
        val timeLeftFormatted = String.format(Locale.getDefault(), "%2d:%02d", minutes, seconds)
        tvCountAllSeconds!!.text = timeLeftFormatted
    }

    private fun stopTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer!!.cancel()
            mCountDownTimer = null
        }
    }

    private fun stopTimer2() {
        if (mCountDownTimer2 != null) {
            mCountDownTimer2!!.cancel()
            Log.d("Привет","Выполнился метод стоптаймер")
        }
    }

    private fun setLevelVisibility() {
        if (level == 1) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 2) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero8!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 3) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero8!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 4) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero8!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 5) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.VISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero8!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 6) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.VISIBLE
            hero6!!.visibility = View.VISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero8!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 7) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.VISIBLE
            hero6!!.visibility = View.VISIBLE
            hero7!!.visibility = View.VISIBLE
            hero8!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 8) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.VISIBLE
            hero6!!.visibility = View.VISIBLE
            hero7!!.visibility = View.VISIBLE
            hero8!!.visibility = View.VISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 9) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.VISIBLE
            hero6!!.visibility = View.VISIBLE
            hero7!!.visibility = View.VISIBLE
            hero8!!.visibility = View.VISIBLE
            hero9!!.visibility = View.VISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 10) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.VISIBLE
            hero2!!.visibility = View.VISIBLE
            hero3!!.visibility = View.VISIBLE
            hero4!!.visibility = View.VISIBLE
            hero5!!.visibility = View.VISIBLE
            hero6!!.visibility = View.VISIBLE
            hero7!!.visibility = View.VISIBLE
            hero8!!.visibility = View.VISIBLE
            hero9!!.visibility = View.VISIBLE
            hero10!!.visibility = View.VISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == 0) {
            tvNameRedScore!!.visibility = View.INVISIBLE
            redScoreCount!!.visibility = View.INVISIBLE
            hero1!!.visibility = View.INVISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.INVISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == -1) {
            tvNameRedScore!!.visibility = View.VISIBLE
            redScoreCount!!.visibility = View.VISIBLE
            redScoreCount!!.text = Integer.toString(level)
            hero1!!.visibility = View.INVISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.VISIBLE
            herominus2!!.visibility = View.INVISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == -2) {
            tvNameRedScore!!.visibility = View.VISIBLE
            redScoreCount!!.visibility = View.VISIBLE
            redScoreCount!!.text = Integer.toString(level)
            hero1!!.visibility = View.INVISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.VISIBLE
            herominus2!!.visibility = View.VISIBLE
            herominus3!!.visibility = View.INVISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == -3) {
            tvNameRedScore!!.visibility = View.VISIBLE
            redScoreCount!!.visibility = View.VISIBLE
            redScoreCount!!.text = Integer.toString(level)
            hero1!!.visibility = View.INVISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.VISIBLE
            herominus2!!.visibility = View.VISIBLE
            herominus3!!.visibility = View.VISIBLE
            herominus4!!.visibility = View.INVISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == -4) {
            tvNameRedScore!!.visibility = View.VISIBLE
            redScoreCount!!.visibility = View.VISIBLE
            redScoreCount!!.text = Integer.toString(level)
            hero1!!.visibility = View.INVISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.VISIBLE
            herominus2!!.visibility = View.VISIBLE
            herominus3!!.visibility = View.VISIBLE
            herominus4!!.visibility = View.VISIBLE
            herominus5!!.visibility = View.INVISIBLE
        } else if (level == -5) {
            tvNameRedScore!!.visibility = View.VISIBLE
            redScoreCount!!.visibility = View.VISIBLE
            redScoreCount!!.text = Integer.toString(level)
            hero1!!.visibility = View.INVISIBLE
            hero2!!.visibility = View.INVISIBLE
            hero3!!.visibility = View.INVISIBLE
            hero4!!.visibility = View.INVISIBLE
            hero5!!.visibility = View.INVISIBLE
            hero6!!.visibility = View.INVISIBLE
            hero7!!.visibility = View.INVISIBLE
            hero9!!.visibility = View.INVISIBLE
            hero10!!.visibility = View.INVISIBLE
            herominus1!!.visibility = View.VISIBLE
            herominus2!!.visibility = View.VISIBLE
            herominus3!!.visibility = View.VISIBLE
            herominus4!!.visibility = View.VISIBLE
            herominus5!!.visibility = View.VISIBLE
            loosing()
        }
    }

    @SuppressLint("ResourceAsColor")
    @Throws(InterruptedException::class)
    private fun winner() {
        val sharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        val countPut = START_TIME_IN_MILLIS - mTimeLeftInMillis
        val countBring = sharedPreferences.getLong(KEY.toString(), Long.MAX_VALUE)
        if (countPut < countBring) {
            editor.putLong(KEY.toString(), countPut)
        }
        editor.apply()
        val builder = AlertDialog.Builder(this@PlayActivity, R.style.AlertDialogTheme)
        builder.setTitle("Победа! Ваше время: " + tvCountAllSeconds!!.text.toString())
            .setCancelable(false)
            .setPositiveButton(
                "Выход"
            ) { dialog, which -> finish() }
            .setNeutralButton(
                "Играть"
            ) { dialog, which ->
                finish()
                val ma = Intent(baseContext, PlayActivity::class.java)
                startActivity(ma)
            }
        val alert = builder.create()
        alert.setOnShowListener {
            val btnPositive = alert.getButton(Dialog.BUTTON_POSITIVE)
            btnPositive.textSize = 31f
            val btnNeuteral = alert.getButton(Dialog.BUTTON_NEUTRAL)
            btnNeuteral.textSize = 31f
        }
        alert.show()
        alert.window!!.setBackgroundDrawableResource(R.drawable.alert_image)
        val textViewId = alert.context.resources.getIdentifier("android:id/alertTitle", null, null)
        val tv = alert.findViewById<View>(textViewId) as TextView
        tv.setTextColor(resources.getColor(R.color.allert_color))
    }

    private fun loosing() {
        val builder = AlertDialog.Builder(this@PlayActivity, R.style.AlertDialogLooseTheme)
        builder.setTitle("Вы проиграли.Ваш уровень: " + redScoreCount!!.text.toString())
            .setCancelable(false)
            .setPositiveButton(
                "Выход"
            ) { dialog, which -> finish() }
            .setNeutralButton(
                "Играть"
            ) { dialog, which ->
                finish()
                val ma = Intent(baseContext, PlayActivity::class.java)
                startActivity(ma)
            }
        val alert = builder.create()
        alert.setOnShowListener {
            val btnPositive = alert.getButton(Dialog.BUTTON_POSITIVE)
            btnPositive.textSize = 31f
            val btnNeuteral = alert.getButton(Dialog.BUTTON_NEUTRAL)
            btnNeuteral.textSize = 31f
        }
        alert.show()
        alert.window!!.setBackgroundDrawableResource(R.drawable.loose)
        val textViewId = alert.context.resources.getIdentifier("android:id/alertTitle", null, null)
        val tv = alert.findViewById<View>(textViewId) as TextView
        tv.setTextColor(resources.getColor(R.color.allert_loose_color))
    }

    @SuppressLint("ResourceAsColor")
    private fun showTrueAnswer() {
        when (rightAnswer) {
            1 -> BtnVar1!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            2 -> BtnVar2!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            3 -> BtnVar3!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            4 -> BtnVar4!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            5 -> {
                BtnVar5!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            }
            6 -> BtnVar6!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            7 -> BtnVar7!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            8 -> BtnVar8!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            9 -> BtnVar9!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
            10 -> BtnVar10!!.setTextColor(ContextCompat.getColor(this, R.color.white_color))
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun resetButtonColor() {
        BtnVar1!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar2!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar3!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar4!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar5!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar6!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar7!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar8!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar9!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
        BtnVar10!!.setTextColor(ContextCompat.getColor(this, R.color.allert_color))
    }

    companion object {
        var START_TIME_IN_MILLIS: Long = 600000000
        private const val TAG = "myLogs"
        private const val SHARED_PREFS = "sharedPrefs"
        private const val KEY = 10
    }
}


