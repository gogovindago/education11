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
        sViewsWithIds.put(R.id.materialCardView, 2);
        sViewsWithIds.put(R.id.profilePic, 3);
        sViewsWithIds.put(R.id.takePhoto, 4);
        sViewsWithIds.put(R.id.llUerType, 5);
        sViewsWithIds.put(R.id.txtUerType, 6);
        sViewsWithIds.put(R.id.spnUserType, 7);
        sViewsWithIds.put(R.id.llPlayingRole, 8);
        sViewsWithIds.put(R.id.txtPlayingRole, 9);
        sViewsWithIds.put(R.id.spnPlayingRole, 10);
        sViewsWithIds.put(R.id.llteamname, 11);
        sViewsWithIds.put(R.id.txtteamname, 12);
        sViewsWithIds.put(R.id.spnteamname, 13);
        sViewsWithIds.put(R.id.btn_uploadprofilepic, 14);
        sViewsWithIds.put(R.id.edtusername, 15);
        sViewsWithIds.put(R.id.edtlastname, 16);
        sViewsWithIds.put(R.id.edtmobile, 17);
        sViewsWithIds.put(R.id.edtBirthdayDate, 18);
        sViewsWithIds.put(R.id.edtemail, 19);
        sViewsWithIds.put(R.id.edtpass, 20);
        sViewsWithIds.put(R.id.edtconfirmpass, 21);
        sViewsWithIds.put(R.id.spnGender, 22);
        sViewsWithIds.put(R.id.btn_register, 23);
        sViewsWithIds.put(R.id.txt_login, 24);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRegisterUserBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private ActivityRegisterUserBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatButton) bindings[23]
            , (androidx.appcompat.widget.AppCompatButton) bindings[14]
            , (com.google.android.material.textfield.TextInputEditText) bindings[18]
            , (com.google.android.material.textfield.TextInputEditText) bindings[21]
            , (com.google.android.material.textfield.TextInputEditText) bindings[19]
            , (com.google.android.material.textfield.TextInputEditText) bindings[16]
            , (com.google.android.material.textfield.TextInputEditText) bindings[17]
            , (com.google.android.material.textfield.TextInputEditText) bindings[20]
            , (com.google.android.material.textfield.TextInputEditText) bindings[15]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[11]
            , (com.google.android.material.card.MaterialCardView) bindings[2]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[3]
            , (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) bindings[1]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[22]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[10]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[7]
            , (androidx.appcompat.widget.AppCompatSpinner) bindings[13]
            , (android.widget.ImageView) bindings[4]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[24]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[12]
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