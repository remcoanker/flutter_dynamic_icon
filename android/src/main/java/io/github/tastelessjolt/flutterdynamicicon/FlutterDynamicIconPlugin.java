package io.github.tastelessjolt.flutterdynamicicon;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

/** FlutterDynamicIconPlugin */
public class FlutterDynamicIconPlugin implements FlutterPlugin, ActivityAware {
  private static final String CHANNEL_NAME = "flutter_dynamic_icon";
  private MethodChannel channel;
  public static ActivityPluginBinding _activityBinding;

  @Override
  public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
    setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPlugin.FlutterPluginBinding binding) {
    teardownChannel();
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    _activityBinding = binding;
  }

  @Override
  public void onDetachedFromActivity() {
    // This is called after the new activity is attached
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    _activityBinding = binding;
  }
  @Override
  public void onDetachedFromActivityForConfigChanges() {
  }

  public static Activity getActivity() {
    return (_activityBinding != null) ? _activityBinding.getActivity() : null;
  }

  private void setupChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, CHANNEL_NAME);
    MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
    channel.setMethodCallHandler(handler);
  }

  private void teardownChannel() {
    channel.setMethodCallHandler(null);
    channel = null;
  }
}
