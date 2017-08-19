package com.BoardiesITSolutions.MessageBarExample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.BoardiesITSolutions.MessageBar.IMessageBar;
import com.BoardiesITSolutions.MessageBar.MessageBar;

public class MainActivity extends AppCompatActivity
{

    Button btnShowMessageWithAutoHide;
    Button btnShowMessageAutoHideDisabled;
    Button btnShowHidingButton;
    Button btnShowWithButtonCallBack;
    Button btnShowPrimaryBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowMessageWithAutoHide = (Button) findViewById(R.id.btnShowWithAutoHide);
        btnShowMessageWithAutoHide.setOnClickListener(showMessageWithAutoHideClickListener);

        btnShowMessageAutoHideDisabled = (Button)findViewById(R.id.btnShowAutoHideDisabled);
        btnShowMessageAutoHideDisabled.setOnClickListener(showMessageAutoHideDisabledClickListener);

        btnShowHidingButton = (Button)findViewById(R.id.btnShowHidingButton);
        btnShowHidingButton.setOnClickListener(showMessageHidingButton);

        btnShowWithButtonCallBack = (Button)findViewById(R.id.btnShowWithButtonCallBack);
        btnShowWithButtonCallBack.setOnClickListener(showMessageWithButtonCallback);

        btnShowPrimaryBackground = (Button)findViewById(R.id.btnShowPrimaryBackground);
        btnShowPrimaryBackground.setOnClickListener(showMessageWithAppPrimaryBackgroundColor);
    }

    protected View.OnClickListener showMessageWithAutoHideClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            MessageBar messageBar = new MessageBar(MainActivity.this);
            messageBar.show("Here is a default message bar");
        }
    };

    protected View.OnClickListener showMessageAutoHideDisabledClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            MessageBar messageBar = new MessageBar(MainActivity.this);
            messageBar.show("Auto hide disabled. Press 'DISMISS' button", true);
        }
    };

    protected View.OnClickListener showMessageHidingButton = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            MessageBar messageBar = new MessageBar(MainActivity.this);
            messageBar.showButton(false);
            messageBar.show("The button is now shown on this message");
        }
    };

    protected View.OnClickListener showMessageWithButtonCallback = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            MessageBar messageBar = new MessageBar(MainActivity.this);
            messageBar.show("You'll receive a toast when clicking button", new IMessageBar()
            {
                @Override
                public void messageButtonClickCallback()
                {
                    Toast.makeText(MainActivity.this, "You clicked the message bar dismiss button", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    protected View.OnClickListener showMessageWithAppPrimaryBackgroundColor = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            MessageBar messageBar = new MessageBar(MainActivity.this);
            messageBar.setMessageBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
            messageBar.show("Message bar background colour has been changed");
        }
    };
}
