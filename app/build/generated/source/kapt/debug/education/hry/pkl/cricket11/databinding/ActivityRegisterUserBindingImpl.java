package education.hry.pkl.cricket11.databinding;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRegisterUserBindingImpl extends ActivityRegisterUserBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.simpleSwipeRefreshLayout, 1);
        sViewsWithIds.put(R.id.llUerType, 2);
        sViewsWithIds.put(R.id.GuestTypePic, 3);
        sViewsWithIds.put(R.id.GuestTypeIcon, 4);
        sViewsWithIds.put(R.id.txtUerTypeGuest, 5);
        sViewsWithIds.put(R.id.PlayerTypePic, 6);
        sViewsWithIds.put(R.id.PlayerTypeIcon, 7);
        sViewsWithIds.put(R.id.txtUerTypePlayer, 8);
        sViewsWithIds.put(R.id.materialCardView, 9);
        sViewsWithIds.put(R.id.profilePic, 10);
        sViewsWithIds.put(R.id.takePhoto, 11);
        sViewsWithIds.put(R.id.cardGuest, 12);
        sViewsWithIds.put(R.id.btn_uploadprofilepic, 13);
        sViewsWithIds.put(R.id.edtusername, 14);
        sViewsWithIds.put(R.id.edtlastname, 15);
        sViewsWithIds.put(R.id.edtmobile, 16);
        sViewsWithIds.put(R.id.edtpass, 17);
        sViewsWithIds.put(R.id.edtconfirmpass, 18);
        sViewsWithIds.put(R.id.cardplayer, 19);
        sViewsWithIds.put(R.id.edtemail, 20);
        sViewsWithIds.put(R.id.edtBirthdayDate, 21);
        sViewsWithIds.put(R.id.llPlayingRole, 22);
        sViewsWithIds.put(R.id.txtPlayingRole, 23);
        sViewsWithIds.put(R.id.spnPlayingRole, 24);
        sViewsWithIds.put(R.id.llteamname, 25);
        sViewsWithIds.put(R.id.txtteamname, 26);
        sViewsWithIds.put(R.id.spnteamname, 27);
        sViewsWithIds.put(R.id.btn_register, 28);
        sViewsWithIds.put(R.id.txt_login, 29);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRegisterUserBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }
    private ActivityRegisterUserBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[4]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[3]
            , (android.widget.ImageView) bindings[7]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[6]
            , (androidx.appcompat.widget.AppCompatButton) bindings[28]
            , (androidx.appcompat.widget.AppCompatButton) bindings[13]
            , (androidx.cardview.widget.CardView) bindings[12]
            , (androidx.cardview.widget.CardView) bindings[19]
            , (com.google.android.material.textfield.TextInputEditText) bindings[21]
            , (com.google.android.material.textfield.TextInputEditText) bindings[18]
            , (com.google.android.material.textfield.TextInputEditText) bindings[20]
            , (com.google.android.material.textfield.TextInputEditText) bindings[15]
            , (com.google.android.material.textfield.TextInputEditText) bindings[16]
            , (com.google.android.material.textfield.TextInputEditText) bindings[17]
            , (com.google.android.material.textfield.TextInputEditText) bindings[14]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[25]
            , (com.google.android.material.card.MaterialCardView) bindings[9]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[10]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[1]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[24]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[27]
            , (android.widget.ImageView) bindings[11]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[29]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[26]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}