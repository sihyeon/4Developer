package com.project4D.fdpay.util;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ViewUtil {
	public static Finder finder(Activity activity) {
		return new ActivityFinder(activity);
	}
	
	public static Finder finder(View view) {
		return new ViewFinder(view);
	}
	
	public interface Finder {
		EditText editText(int id);

		Button button(int id);

		String text(EditText et);

		TextView textView(int id);

		ListView listView(int id);

		ImageView imageView(int id);
	}
	
	private static class ActivityFinder implements Finder {
		private final Activity activity;

		public ActivityFinder(Activity activity) {
			this.activity = activity;
		}

		public EditText editText(int id) {
			return (EditText) activity.findViewById(id);
		}

		public Button button(int id) {
			return (Button) activity.findViewById(id);
		}

		public String text(EditText et) {
			return et.getText().toString();
		}

		public TextView textView(int id) {
			return (TextView) activity.findViewById(id);
		}

		public ListView listView(int id) {
			return (ListView) activity.findViewById(id);
		}

		@Override
		public ImageView imageView(int id) {
			return (ImageView) activity.findViewById(id);
		}
	}
	
	private static class ViewFinder implements Finder {
		private final View vie;

		public ViewFinder(View activity) {
			this.vie = activity;
		}

		public EditText editText(int id) {
			return (EditText) vie.findViewById(id);
		}

		public Button button(int id) {
			return (Button) vie.findViewById(id);
		}

		public String text(EditText et) {
			return et.getText().toString();
		}

		public TextView textView(int id) {
			return (TextView) vie.findViewById(id);
		}

		public ListView listView(int id) {
			return (ListView) vie.findViewById(id);
		}
		
		@Override
		public ImageView imageView(int id) {
			return (ImageView) vie.findViewById(id);
		}
	}
}
