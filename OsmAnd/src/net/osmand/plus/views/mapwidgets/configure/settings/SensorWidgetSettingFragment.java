package net.osmand.plus.views.mapwidgets.configure.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import net.osmand.plus.R;
import net.osmand.plus.plugins.PluginsHelper;
import net.osmand.plus.plugins.externalsensors.DeviceType;
import net.osmand.plus.plugins.externalsensors.ExternalSensorsPlugin;
import net.osmand.plus.plugins.externalsensors.devices.AbstractDevice;
import net.osmand.plus.plugins.externalsensors.devices.sensors.SensorTextWidget;
import net.osmand.plus.plugins.externalsensors.dialogs.SelectExternalDeviceFragment;
import net.osmand.plus.utils.UiUtilities;
import net.osmand.plus.views.mapwidgets.MapWidgetInfo;
import net.osmand.plus.views.mapwidgets.WidgetType;

public class SensorWidgetSettingFragment extends WidgetSettingsBaseFragment {

	private SensorTextWidget sensorWidget;
	protected ExternalSensorsPlugin plugin;
	private UiUtilities uiUtils;

	private WidgetType widgetType = WidgetType.RSSI;
	private AbstractDevice<?> sourceDevice;
	private boolean newSourceDeviceSet;
	private AppCompatImageView deviceIcon;

	@NonNull
	@Override
	public WidgetType getWidget() {
		return widgetType;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		plugin = PluginsHelper.getPlugin(ExternalSensorsPlugin.class);
		uiUtils = app.getUIUtilities();
	}

	@Override
	protected void initParams(@NonNull Bundle bundle) {
		super.initParams(bundle);
		MapWidgetInfo widgetInfo = widgetRegistry.getWidgetInfoById(widgetId);
		if (widgetInfo != null) {
			widgetType = widgetInfo.getWidgetType();
			sensorWidget = ((SensorTextWidget) widgetInfo.widget);
		} else {
			dismiss();
		}
	}

	public void selectNewDevice(AbstractDevice<?> device) {
		sourceDevice = device;
		newSourceDeviceSet = true;
		updateSourceDeviceUI();
	}

	@Override
	protected void setupContent(@NonNull LayoutInflater themedInflater, @NonNull ViewGroup container) {
		themedInflater.inflate(R.layout.sensor_widget_settings_fragment, container);
		deviceIcon = view.findViewById(R.id.device_icon);
		setupDataSource();
		view.findViewById(R.id.widget_source_card).setOnClickListener((v) -> {
			SelectExternalDeviceFragment.showInstance(requireActivity().getSupportFragmentManager(), this, sensorWidget.getFieldType(), getSourceDeviceId());
		});
	}

	@Nullable
	private String getSourceDeviceId() {
		return sourceDevice == null ? null : sourceDevice.getDeviceId();
	}

	private void setupDataSource() {
		if (sensorWidget != null && sensorWidget.getWidgetSensor() != null) {
			sensorWidget.getDeviceId(appMode);
			sourceDevice = sensorWidget.getWidgetSensor().getDevice();
		}
		updateSourceDeviceUI();
	}

	private void updateSourceDeviceUI() {
		if (sourceDevice == null) {
			deviceIcon.setImageDrawable(uiUtils.getIcon(R.drawable.ic_action_sensor, nightMode));
			((TextView) view.findViewById(R.id.device_name)).setText(R.string.shared_string_none);
		} else {
			((TextView) view.findViewById(R.id.device_name)).setText(sourceDevice.getName());
			DeviceType deviceType = sourceDevice.getDeviceType();
			deviceIcon.setImageResource(nightMode ? deviceType.nightIconId : deviceType.dayIconId);
		}
	}

	@Override
	protected void applySettings() {
		sensorWidget.setDeviceId(appMode, getSourceDeviceId());
	}
}