package com.elong.elongaarlib.customview;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elong.elongaarlib.R;


/**
 * @author Ang.Chen 自定义EditText和iamgeView实现点击imageview清空EditText的功能
 */
public class CustomRelativeLayout extends RelativeLayout implements android.view.View.OnFocusChangeListener,
        android.view.View.OnClickListener, TextWatcher {

    private int DELETE_IMAGEVIEW_ID = 1001;

    private Context mContext;
    private EditText editText;
    private ImageView imageView;

    /**
     * @return imageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    public static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
        // TODO Auto-generated constructor stub
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomRelativeLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public void setTextSize(int textSize) {
        editText.setTextSize(textSize);
    }

    public void setHint(int stringID) {
        editText.setHint(stringID);
    }

    public void setText(int stringID) {
        editText.setText(stringID);
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setHint(String hint) {
        editText.setHint(hint);
    }

    public void setTextColor(int textColor) {
        editText.setTextColor(textColor);
    }

    public String getText() {
        return editText.getText().toString().trim();
    }

    public EditText getEditText() {
        return editText;
    }

    private void init(AttributeSet attrs) {
        init();
        int inputTypeValue = attrs.getAttributeIntValue(NAMESPACE_ANDROID, "inputType", -1);
        if (-1 != inputTypeValue) {
            editText.setInputType(inputTypeValue);
        }
        int hintValue = attrs.getAttributeResourceValue(NAMESPACE_ANDROID, "hint", -1);

        if (-1 != hintValue) {
            editText.setHint(mContext.getString(hintValue));
        }
        int maxLength = attrs.getAttributeIntValue(NAMESPACE_ANDROID, "maxLength", -1);
        if (maxLength > 0) {
            InputFilter[] filterArray = new InputFilter[1];
            filterArray[0] = new InputFilter.LengthFilter(maxLength);
            editText.setFilters(filterArray);
        }
        boolean singleLine = attrs.getAttributeBooleanValue(NAMESPACE_ANDROID, "singleLine", true);
        if (!singleLine) {
            editText.setSingleLine(singleLine);
        }
        float textSize = attrs.getAttributeIntValue(NAMESPACE_ANDROID, "textSize", 15);
        if (textSize > 0) {
            editText.setTextSize(textSize);
        }

    }

    private void init() {
        setGravity(Gravity.CENTER_VERTICAL);
        editText = (EditText) View.inflate(mContext, R.layout.payment_edit_text_input, null);
        editText.setTextColor(Color.BLACK);
        editText.setOnFocusChangeListener(this);
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && imageView != null && imageView.getVisibility() == View.VISIBLE) {
                    imageView.setVisibility(View.GONE);
                }
            }
        });

        imageView = new ImageView(mContext);
        imageView.setId(DELETE_IMAGEVIEW_ID);
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        imageView.setBackgroundResource(R.mipmap.city_keyword_select_closeicon);
        imageParams.rightMargin = 20;
        imageParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        imageView.setLayoutParams(imageParams);
        addView(imageView);
        imageView.setOnClickListener(this);
        imageView.setVisibility(View.GONE);

        addView(editText);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) editText.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;
        layoutParams.addRule(RelativeLayout.LEFT_OF, imageView.getId());
        editText.setLayoutParams(layoutParams);
    }

    public void setEditTextID(int id) {
        editText.setId(id);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        onEditTextFocusChange(v, hasFocus);
    }

    /**
     * 当外部需要给EditText设置焦点改变监听的时候，需要在焦点改变的时候回调一下本方法
     *
     * @param v
     * @param hasFocus
     */
    public void onEditTextFocusChange(View v, boolean hasFocus) {
        String trim = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            if (hasFocus) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        editText.setText("");
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == null || s.length() == 0 || "".equals(s.toString().trim())) {
            imageView.setVisibility(View.GONE);
            return;
        }
        if (editText.hasFocus()) {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    public void addTextChangedListener(TextWatcher watcher) {
        editText.addTextChangedListener(watcher);
    }
}
