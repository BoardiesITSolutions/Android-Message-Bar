[![](https://jitpack.io/v/BoardiesITSolutions/Android-Message-Bar.svg)](https://jitpack.io/#BoardiesITSolutions/Android-Message-Bar)
[Chat to us on Slack](https://join.slack.com/t/boardiesitsolutions/shared_invite/enQtMjQ2MTQ2OTIzOTI1LTJkYjQ5Zjc1ZjZmNDQ0MGQ3ZjM1NDFlZTI5ZmE3NjQ1ZjRkOTQ1NTliMmI0ZTMyOTViNWJkMWQ0NWUyM2Q2MjQ)

# Android-Message-Bar
Simple and lightweight Message Bar Notification

Android Message Bar is a simple and lightweight library supporting Android API Level 15 (Android 4.x) and up. It displays a customisable message bar with an optional button similar to what you might find on some Google apps such as Gmail. 

Installing and using the library couldn't be simpler, just follow the steps below.

# Adding the library as a dependency in build.gradle

1. First of all, we need to add the repository to where to get the library form. The library is available through [JitPack.io](https://jitpack.io/). This allows you to reference the library directly from GitHub repository for easy use. 
In your projects root build.gradle file add the following
```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

2. In the apps build.gradle (the directory where your source code is, the directory is probably called `app`), you need to add the AndroidMessageBar library to the dependency section of the gradle file as follows:
```
compile 'com.github.BoardiesITSolutions:Android-Message-Bar:1.0'
```

Change the 1.0 on the end of the dependency line with the latest version number shown in the tags on GitHub. 

3. Sync your gradle files and rebuild your project and that's it, you can start using the library. 

# Using the Android Message Bar Library
Within your `Activity` or `FragmentActivity` where you want to display the message bar, create a new instance of the MessageBar class passing in the current `Activity` or `FragmentActivity` as a parameter to the constructor as shown below. 
```
MessageBar messageBar = new MessageBar(this);
```

To show the default message bar you can then call the show method passing in a string as a parameter, the string being what message is to be displayed as shown below:

```
messageBar.show("Here is a message");
```

# Customising the Android Message Bar
There a couple of methods to allow you to customise the message. There are 3 different show methods that are supported by the MessageBar class. 

By default the message bar will automatically hide after 5 seconds, if you want to disable this, then you can use the following version of the show method to disable the auto hide feature. 
```
show("Here is a message", true);
```

You can also pass an `IMessageBar` interface as the second parameter to the show method. This will allow you to receive the event of the message bar button being clicked. You can do this using the following:

```
messageBar.show("You'll receive a toast when clicking button", new IMessageBar()
{
  @Override
  public void messageButtonClickCallback()
  {
    Toast.makeText(MainActivity.this, "You clicked the message bar dismiss button", Toast.LENGTH_LONG).show();
  }
});
```

There are also a couple of methods to allow you to override the styling of the message bar, such as the background colour, the message text colour and the button text colour or to 

- To change the message bar background colour, run the following:
```
messageBar.setMessageBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
```

- To change the message bar text colour, run the following:
```
messageBar.setMessageTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_bright));
```

- To change the message button text colour, run the following:
```
messageBar.setButtonTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_purple));
```

You can also hide the message bar button if you don't need it using the following;
```
messageBar.showButton(false);
```

You can also override the default button text by running the following:
```
messageBar.setButtonText("New Button Text");
```

There's an example app project in the root of the project that demos the Android Message Bar and shows how to implement it. 

If you have any questions, feature requests or find any bugs, then please report them either via the GitHub repository issue tracker or via our own issuer tracker on our [Support Portal](https://support.boardiesitsolutions.com). 
