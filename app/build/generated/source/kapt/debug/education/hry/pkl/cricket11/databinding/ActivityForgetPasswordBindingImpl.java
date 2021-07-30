package education.hry.pkl.cricket11.databinding;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityForgetPasswordBindingImpl extends ActivityForgetPasswordBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(17);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar_layout"},
            new int[] {1},
            new int[] {education.hry.pkl.cricket11.R.layout.toolbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayout2, 2);
        sViewsWithIds.put(R.id.ScrollView, 3);
        sViewsWithIds.put(R.id.txtcountdown, 4);
        sViewsWithIds.put(R.id.member_login_tv, 5);
        sViewsWithIds.put(R.id.llll, 6);
        sViewsWithIds.put(R.id.txtmobile, 7);
        sViewsWithIds.put(R.id.llofrgotpassword, 8);
        sViewsWithIds.put(R.id.txtforget, 9);
        sViewsWithIds.put(R.id.txtmsg, 10);
        sViewsWithIds.put(R.id.llotpbox, 11);
        sViewsWithIds.put(R.id.otp_view, 12);
        sViewsWithIds.put(R.id.generateotp_button, 13);
        sViewsWithIds.put(R.id.verify_button, 14);
        sViewsWithIds.put(R.id.btnpassword, 15);
        sViewsWithIds.put(R.id.view_1, 16);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityForgetPasswordBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private ActivityForgetPasswordBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ScrollView) bindings[3]
            , (android.widget.Button) bindings[15]
            , (android.widget.Button) bindings[13]
            , (android.widget.LinearLayout) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[6]
            , (com.google.android.material.textfield.TextInputLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.TextView) bindings[5]
            , (in.aabhasjindal.otptextview.OtpTextView) bindings[12]
            , (education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (com.google.android.material.textfield.TextInputEditText) bindings[9]
            , (com.google.android.material.textfield.TextInputEditText) bindings[7]
            , (android.widget.TextView) bindings[10]
            , (android.widget.Button) bindings[14]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[16]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setContainedBinding(this.toolbar);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        toolbar.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (toolbar.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        toolbar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeToolbar((education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeToolbar(education.hry.pkl.cricket11.databinding.ToolbarLayoutBinding Toolbar, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(toolbar);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): toolbar
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}