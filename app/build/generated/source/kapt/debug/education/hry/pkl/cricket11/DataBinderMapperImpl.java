package education.hry.pkl.cricket11;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import education.hry.pkl.cricket11.databinding.ActivityCareerStatisticsBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityEducationwebsiteBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityForgetPasswordBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityMatchDetailsBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityNotificationDetailBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityNotificationsBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityPlayerDetailBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityPlayerHistoryBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityProfileBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityResetPasswordBindingImpl;
import education.hry.pkl.cricket11.databinding.ActivityWelcomeBindingImpl;
import education.hry.pkl.cricket11.databinding.ListViewItemRowBindingImpl;
import education.hry.pkl.cricket11.databinding.ToolbarLayoutBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCAREERSTATISTICS = 1;

  private static final int LAYOUT_ACTIVITYEDUCATIONWEBSITE = 2;

  private static final int LAYOUT_ACTIVITYFORGETPASSWORD = 3;

  private static final int LAYOUT_ACTIVITYMATCHDETAILS = 4;

  private static final int LAYOUT_ACTIVITYNOTIFICATIONDETAIL = 5;

  private static final int LAYOUT_ACTIVITYNOTIFICATIONS = 6;

  private static final int LAYOUT_ACTIVITYPLAYERDETAIL = 7;

  private static final int LAYOUT_ACTIVITYPLAYERHISTORY = 8;

  private static final int LAYOUT_ACTIVITYPROFILE = 9;

  private static final int LAYOUT_ACTIVITYRESETPASSWORD = 10;

  private static final int LAYOUT_ACTIVITYWELCOME = 11;

  private static final int LAYOUT_LISTVIEWITEMROW = 12;

  private static final int LAYOUT_TOOLBARLAYOUT = 13;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(13);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_career_statistics, LAYOUT_ACTIVITYCAREERSTATISTICS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_educationwebsite, LAYOUT_ACTIVITYEDUCATIONWEBSITE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_forget_password, LAYOUT_ACTIVITYFORGETPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_match_details, LAYOUT_ACTIVITYMATCHDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_notification_detail, LAYOUT_ACTIVITYNOTIFICATIONDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_notifications, LAYOUT_ACTIVITYNOTIFICATIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_player_detail, LAYOUT_ACTIVITYPLAYERDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_player_history, LAYOUT_ACTIVITYPLAYERHISTORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_reset_password, LAYOUT_ACTIVITYRESETPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.activity_welcome, LAYOUT_ACTIVITYWELCOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.list_view_item_row, LAYOUT_LISTVIEWITEMROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(education.hry.pkl.cricket11.R.layout.toolbar_layout, LAYOUT_TOOLBARLAYOUT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYCAREERSTATISTICS: {
          if ("layout/activity_career_statistics_0".equals(tag)) {
            return new ActivityCareerStatisticsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_career_statistics is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEDUCATIONWEBSITE: {
          if ("layout/activity_educationwebsite_0".equals(tag)) {
            return new ActivityEducationwebsiteBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_educationwebsite is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYFORGETPASSWORD: {
          if ("layout/activity_forget_password_0".equals(tag)) {
            return new ActivityForgetPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_forget_password is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMATCHDETAILS: {
          if ("layout/activity_match_details_0".equals(tag)) {
            return new ActivityMatchDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_match_details is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNOTIFICATIONDETAIL: {
          if ("layout/activity_notification_detail_0".equals(tag)) {
            return new ActivityNotificationDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_notification_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYNOTIFICATIONS: {
          if ("layout/activity_notifications_0".equals(tag)) {
            return new ActivityNotificationsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_notifications is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPLAYERDETAIL: {
          if ("layout/activity_player_detail_0".equals(tag)) {
            return new ActivityPlayerDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_player_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPLAYERHISTORY: {
          if ("layout/activity_player_history_0".equals(tag)) {
            return new ActivityPlayerHistoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_player_history is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPROFILE: {
          if ("layout/activity_profile_0".equals(tag)) {
            return new ActivityProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYRESETPASSWORD: {
          if ("layout/activity_reset_password_0".equals(tag)) {
            return new ActivityResetPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_reset_password is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYWELCOME: {
          if ("layout/activity_welcome_0".equals(tag)) {
            return new ActivityWelcomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_welcome is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTVIEWITEMROW: {
          if ("layout/list_view_item_row_0".equals(tag)) {
            return new ListViewItemRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_view_item_row is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARLAYOUT: {
          if ("layout/toolbar_layout_0".equals(tag)) {
            return new ToolbarLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_layout is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(13);

    static {
      sKeys.put("layout/activity_career_statistics_0", education.hry.pkl.cricket11.R.layout.activity_career_statistics);
      sKeys.put("layout/activity_educationwebsite_0", education.hry.pkl.cricket11.R.layout.activity_educationwebsite);
      sKeys.put("layout/activity_forget_password_0", education.hry.pkl.cricket11.R.layout.activity_forget_password);
      sKeys.put("layout/activity_match_details_0", education.hry.pkl.cricket11.R.layout.activity_match_details);
      sKeys.put("layout/activity_notification_detail_0", education.hry.pkl.cricket11.R.layout.activity_notification_detail);
      sKeys.put("layout/activity_notifications_0", education.hry.pkl.cricket11.R.layout.activity_notifications);
      sKeys.put("layout/activity_player_detail_0", education.hry.pkl.cricket11.R.layout.activity_player_detail);
      sKeys.put("layout/activity_player_history_0", education.hry.pkl.cricket11.R.layout.activity_player_history);
      sKeys.put("layout/activity_profile_0", education.hry.pkl.cricket11.R.layout.activity_profile);
      sKeys.put("layout/activity_reset_password_0", education.hry.pkl.cricket11.R.layout.activity_reset_password);
      sKeys.put("layout/activity_welcome_0", education.hry.pkl.cricket11.R.layout.activity_welcome);
      sKeys.put("layout/list_view_item_row_0", education.hry.pkl.cricket11.R.layout.list_view_item_row);
      sKeys.put("layout/toolbar_layout_0", education.hry.pkl.cricket11.R.layout.toolbar_layout);
    }
  }
}
