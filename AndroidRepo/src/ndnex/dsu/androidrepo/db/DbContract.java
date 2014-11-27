
package ndnex.dsu.androidrepo.db;

import android.provider.BaseColumns;

/**
 * Contract class for DbHelper.
 * @author Mengjin
 *
 */
public class DbContract {
	//public static final String CONTENT_AUTHORITY = "ndnex.dsu.androidrepo.db";
	//private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	
	public DbContract() {}
	
	/* Inner class that defines the device information contents*/
	public static abstract class DeviceInfo implements BaseColumns {
		public static final String TABLE_NAME = "device_info";
		public static final String COLUMN_NAME_DEVICE_NAME = "device_name";
		public static final String COLUMN_NAME_DEVICE_TYPE = "device_type";
	}
	
	public static abstract class ACL implements BaseColumns {
		public static final String TABLE_NAME = "acl";
		public static final String COLUMN_NAME_APP_NAME = "app_name";
		public static final String COLUMN_NAME_APP_PUBLIC_KEY = "app_public_key";
		public static final String COLUMN_NAME_APP_FILTER = "app_filter";
	}
	
	public static abstract class Location implements BaseColumns {
		public static final String TABLE_NAME = "location";
		public static final String COLUMN_NAME_TIME_STAMP = "time_stamp";
		public static final String COLUMN_NAME_LATITUDE = "latitude";
		public static final String COLUMN_NAME_LONGITUDE = "longitude";
	}
	
	public static abstract class SensorData implements BaseColumns {
		public static final String COLUMN_NAME_TIMESTAMP = "time_stamp";
		public static final String COLUMN_NAME_DATA = "data";
		public static final String COLUMN_NAME_ACCURACY = "accuracy";
	}
}
