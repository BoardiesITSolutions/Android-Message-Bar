package com.BoardiesITSolutions.MessageBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by Chris Board on 17/08/2017.
 * (C) Copyright 2017 - Boardies IT Solutions
 */

public class MessageBar extends RelativeLayout
{
    private Activity activityObj;
    private TextView txtMessage;
    private Button messageButton;
    private IMessageBar iMessageBar;

    /**
     * Create a new MessageBar from an activity or fragment activity
     * @param activity
     */
    public MessageBar(Activity activity)
    {
        super(activity);
        this.activityObj = activity;
        init();
    }

    /**
     * Inflates the layout and set the gui component class members
     */
    private void init()
    {
        inflate(getContext(), R.layout.message_bar, this);

        this.txtMessage = findViewById(R.id.message_text);
        this.messageButton = findViewById(R.id.message_button);
        this.messageButton.setOnClickListener(messageButtonClickListener);
    }

    /**
     * Hide the button from the message bar
     * @param showButton True to show the button, otherwise false
     */
    public void showButton(boolean showButton)
    {
        if (showButton)
        {
            messageButton.setVisibility(View.VISIBLE);
        }
        else
        {
            messageButton.setVisibility(View.GONE);
        }
    }

    /**
     * Programatically assign the colour of the button text from a colour resource.
     * @param textColor
     */
    public void setButtonTextColor(int textColor)
    {
        this.messageButton.setTextColor(textColor);
    }

    /**
     * Programatically assign the colour of the message text from a colour resource
     * @param textColor
     */
    public void setMessageTextColor(int textColor)
    {
        this.txtMessage.setTextColor(textColor);
    }

    /**
     * Programatically assign the background colour of the message bar from a colour resource
     * @param backgroundColor
     */
    public void setMessageBackgroundColor(int backgroundColor)
    {
        findViewById(R.id.container).setBackgroundColor(backgroundColor);
    }

    /**
     * Override the default message bar button text (The default is "DISMISS")
     * @param buttonText
     */
    public void setButtonText(String buttonText)
    {
        messageButton.setText(buttonText);
    }

    /**
     * Show the message bar with defaults with the defined message
     * @param message The message that should be displayed on the message bar
     */
    public void show(String message)
    {
        try
        {
            this.show(message, false, null);
        }
        catch (Exception e)
        {
            Log.e("MessageBar", e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Show the message bar with the defined message and set whether or not the message bar will automatically hide after 5 seconds
     * @param message The message that should be displayed on the message bar
     * @param disableAutoHide If true, the message bar won't automatically disappear
     */
    public void show(String message, boolean disableAutoHide)
    {
        try
        {
            this.show(message, disableAutoHide, null);
        }
        catch (Exception e)
        {
            Log.e("MessageBar", e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Show the message bar with the defined message with a callback to the button click
     * @param message The message that should be displayed on the message bar
     * @param iMessageBar The call back interface, this allows you to receive a click callback when the message button is clicked
     */
    public void show(String message, IMessageBar iMessageBar)
    {
        try
        {
            this.show(message, false, iMessageBar);
        }
        catch (Exception e)
        {
            Log.e("MessageBar", e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Show the message bar with the defined message, choose whether or not to auto hide the message bar and a call back interface, to allow you to receive a click call back when the message bar button is clicked
     * @param message The message that should be displayed on the message bar
     * @param disableAutoHide If true then the message bar will not automatically hide after 5 secondss
     * @param iMessageBar The call back interface, this allows you to receive a click callback when the message button is clicked
     * @throws Exception If an invalid activity class has been passed to constructor. You do not need to catch this yourself, it will catch internally and log the error to the logcat
     */
    public void show(String message, boolean disableAutoHide, IMessageBar iMessageBar) throws Exception
    {
        this.txtMessage.setText(message);
        this.iMessageBar = iMessageBar;
        if (activityObj != null)
        {
            this.setAlpha(0);
            activityObj.addContentView(this, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            this.animate().setDuration(500).alpha(1).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    MessageBar.this.setVisibility(View.VISIBLE);
                }
            });

        }
        else
        {
            throw new Exception("Invalid Activity object. Only Activity is supported");
        }

        if (!disableAutoHide)
        {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    hideMessage();
                }
            }, 5000);
        }

    }

    /**
     * Click handler for the message bar button. Hides the message bar, and if the call back interface is not null,
     * it will call the interface method.
     */
    protected OnClickListener messageButtonClickListener = new OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            hideMessage();
            if (iMessageBar != null)
            {
                iMessageBar.messageButtonClickCallback();
            }
        }
    };

    /**
     * Animate the hiding of the message bar so that it fades out.
     */
    private void hideMessage()
    {
        this.animate().setDuration(1000).alpha(0).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                MessageBar.this.setVisibility(View.GONE);
            }
        });
    }
}

