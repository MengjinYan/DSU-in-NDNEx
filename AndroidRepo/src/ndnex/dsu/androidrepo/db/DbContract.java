
package ndnex.dsu.androidrepo.db;

import android.provider.BaseColumns;

/**
 * Contract class for DbHelper.
 * @author Mengjin
 *
 */
public class DbContract {
	public DbContract() {}
	
	/* Inner class that defines the device information contents*/
	public static abstract class DeviceInfo implements BaseColumns {
		public static final String TABLE_NAME = "device_info";
		public static final String COLUMN_NAME_DEVICE_NAME = "device_name";//TEXT/String
		public static final String COLUMN_NAME_DEVICE_TYPE = "device_type";//INT
	}
	
	public static abstract class WL implements BaseColumns {
		public static final String TABLE_NAME = "wl";
		public static final String COLUMN_NAME_APP_NAME = "app_name";//BLOB
		public static final String COLUMN_NAME_APP_PUBLIC_KEY_NAME = "app_public_key_name";//BLOB
		public static final String COLUMN_NAME_APP_FILTER = "app_filter";//BLOB
		public static final String COLUMN_NAME_APP_EXPIRE_TIME = "app_expire_time";//TEXT/String
	}
	
	public static abstract class BL implements BaseColumns {
		public static final String TABLE_NAME = "bl";
		public static final String COLUMN_NAME_APP_NAME = "app_name";//BLOB
		public static final String COLUMN_NAME_APP_PUBLIC_KEY_NAME = "app_public_key_name";//BLOB
	}
	
	public static abstract class Location implements BaseColumns {
		public static final String TABLE_NAME = "location";
		public static final String COLUMN_NAME_TIME_STAMP = "time_stamp";//INT
		public static final String COLUMN_NAME_LATITUDE = "latitude";//FLOAT
		public static final String COLUMN_NAME_LONGITUDE = "longitude";//FLOAT
	}
	
	public static abstract class SensorData implements BaseColumns {
		public static final String COLUMN_NAME_TIMESTAMP = "time_stamp";//INT
		public static final String COLUMN_NAME_DATA = "data";//BLOB
		public static final String COLUMN_NAME_ACCURACY = "accuracy";//INT
	}
}
