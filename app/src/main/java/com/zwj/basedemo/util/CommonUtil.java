package com.zwj.basedemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwj.basedemo.R;

import java.text.DecimalFormat;

public class CommonUtil {

    private CommonUtil() {
    }


    /**
     * 复制内容到剪贴板
     *
     * @param content
     */
//    public static void copyToClipBoard(String content) {
//        ClipboardManager cm = (ClipboardManager) MyApplication.getGlobalContext().getSystemService(Context.CLIPBOARD_SERVICE);
//        cm.setText(content);
//        ToastUtil.toast("复制成功");
//    }

    /**
     * 纠正url转json的时候出现错误
     */
    public static String correctUrlString(String content) {
        return content.replace("\\/", "/");
    }

    /**
     * 点击ImageView的时候添加压黑效果
     *
     * @param iv
     */
    public static void addLayerForImageview(ImageView iv) {
        iv.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        changeLight((ImageView) view, 0);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        changeLight((ImageView) view, -80);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        changeLight((ImageView) view, 0);
                        break;
                    default:
                        changeLight((ImageView) view, 0);
                        break;
                }
                return false;
            }
        });
    }

    private static void changeLight(ImageView imageview, int brightness) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        imageview.setColorFilter(new ColorMatrixColorFilter(matrix));
    }

    /**
     * 拨打电话
     * @param activity
     * @param phoneNum
     */
    public static void call(Activity activity, String phoneNum) {
//        MobclickAgent.onEvent(activity, Constant.UM_EVENT_DIAL); // 统计拨打电话
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
//        activity.startActivity(intent);
    }

    /***
     * 隐藏手机号码中间四位
     */
    public static String hidePhoneNum(String phone) {
        if (phone.length() > 7) {
            return phone.substring(0, 3) + "****"
                    + phone.substring(7, phone.length());
        }
        return phone;
    }

    /**
     * 半角转换为全角 ，用于解决textview自动换行问题
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

//    /**
//     * 保留两位小数,强转成double之后如果小数点之后是".00"就会变成".0"
//     *
//     * @param number
//     * @return
//     */
//    public static double keepTwoPlacesOfDecimal(double number) {
//        return keepPlacesOfDecimal(number, 2);
//    }
//
//    /**
//     * 保留两位小数,强转成double之后如果小数点之后是".00"就会变成".0"
//     *
//     * @param number
//     * @param places 保留的小数位数
//     * @return
//     */
//    public static double keepPlacesOfDecimal(double number, int places) {
//        BigDecimal b = new BigDecimal(number);
//        return b.setScale(places, BigDecimal.ROUND_HALF_UP).doubleValue();
//    }

    public static String fromaterTwoPlacesOfDecimal(double number) {
        if (number != 0) {
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(number);
        } else {
            return "0";
        }
    }

    public static void showView(View view, boolean isShow) {
        if (view != null) {
            if (isShow) {
                if (view.getVisibility() == View.GONE
                        || view.getVisibility() == View.INVISIBLE) {
                    view.setVisibility(View.VISIBLE);
                }
            } else {
                if (view.getVisibility() == View.VISIBLE) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 获取当前应用的信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是当前类的包名，0代表是获取版本信息
            info = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 把编号的字符串格式化为每四位用空格隔开
     *
     * @param numberStr
     */
    public static String formatCarNo(String numberStr) {
        if(TextUtils.isEmpty(numberStr)) {
            return null;
        }

        StringBuilder sbCarNo = new StringBuilder();
        int count = numberStr.length() / 4;
        int index = numberStr.length() % 4;

        if (index != 0) {
            sbCarNo.append(numberStr.substring(0, index));
        }

        for (int i = 0; i < count; i++) {
            if (sbCarNo.length() > 0) {
                sbCarNo.append("  ");
            }

            sbCarNo.append(numberStr.substring(index + i * 4, index + (i + 1)
                    * 4));
        }

        return sbCarNo.toString();
    }

    /**
     * 对传入的图片路径进行大小图的路径拼接
     *
     * @param picUrl
     * @return 返回一个picUrl是的字符串数组，下标为0的是小图，下标为1的是大图
     */
    public static String[] formatPicUrl(String picUrl) {
        String[] picUrls = new String[2];

        if (picUrl.startsWith("http://")) {
            if (TextUtils.isEmpty(picUrl)) {
                picUrls[0] = null;
                picUrls[1] = null;
            } else {
                StringBuilder sbPicUrl = new StringBuilder();
                if (picUrl.contains("_Big.")) {
                    picUrls[1] = picUrl;

                    int doctIndex = picUrl.lastIndexOf(".");
                    String preString = picUrl.substring(0, doctIndex - 4);
                    String suffixString = picUrl.substring(doctIndex,
                            picUrl.length());
                    sbPicUrl.append(preString).append(suffixString);
                    picUrls[0] = sbPicUrl.toString();
                } else {
                    picUrls[0] = picUrl;

                    int doctIndex = picUrl.lastIndexOf(".");
                    String preString = picUrl.substring(0, doctIndex);
                    String suffixString = picUrl.substring(doctIndex,
                            picUrl.length());
                    sbPicUrl.append(preString).append("_Big").append(suffixString);
                    picUrls[1] = sbPicUrl.toString();
                }

                LogUtils.sysout("small pic url ---> " + picUrls[0]);
                LogUtils.sysout("big pic url ---> " + picUrls[1]);
            }
        } else {
            picUrls[0] = picUrl;
            picUrls[1] = picUrl;
        }

        return picUrls;
    }

    /**
     * 生成城市数据库
     * @param mContext
     * @return
     */
//	public static boolean createCityDB(Context mContext) {
//		boolean isSuccessful = false;
//		String jsonString = FileUtils.loadContentFromAssets(mContext,
//				Constant.FILE_CITY_NAME);
//		List<CityBean> provinceList = JsonUtil.getObjects(jsonString,
//				CityBean.class);
//		if (provinceList != null) {
//			CityDAO cityDAO = new CityDAO(mContext);
//			isSuccessful = cityDAO.addAllData(provinceList);
//		}
//
//		return isSuccessful;
//	}

    /**
     * 是否可以往上滚动
     */
    public static boolean canChildScrollUp(View view) {
        if (view == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(view, -1) || view.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(view, -1);
        }
    }

    /**
     * 是否可以往下滚动
     *
     * @param view
     * @return
     */
    public static boolean canChildScrollDown(View view) {
        if (view == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                if (absListView.getChildCount() > 0) {
                    int lastChildBottom = absListView.getChildAt(absListView.getChildCount() - 1).getBottom();
                    return absListView.getLastVisiblePosition() == absListView.getAdapter().getCount() - 1 && lastChildBottom <= absListView.getMeasuredHeight();
                } else {
                    return false;
                }

            } else {
                return ViewCompat.canScrollVertically(view, 1) || view.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(view, 1);
        }
    }

    /**
     * 若value不为空，则将数据传入intent中
     */
    public static void putExtra(Intent intent, String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            intent.putExtra(key, value);
        }
    }
//
//    /***
//     * 测量ListView的高度
//     *
//     * @param listView
//     */
//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//        // 获取ListView对应的Adapter
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(0, 0); // 计算子项View 的宽高
//            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        // listView.getDividerHeight()获取子项间分隔符占用的高度
//        // params.height最后得到整个ListView完整显示需要的高度
//        listView.setLayoutParams(params);
//    }

    /**
     * 为车辆全名后面添加热门和优惠图标
     */
    public static void addImage(Context context, TextView textView, String content, boolean isHotCar, boolean isAgioCar) {
        if (!isHotCar && !isAgioCar) {
            textView.setText(content);
            return;
        }
        int maxLines = 2;
        textView.setMaxLines(maxLines);// 设置最大行数
        Drawable drawableHot = null;
        Drawable drawableAgio = null;

        TextPaint paint = textView.getPaint();// 获取文本控件字体样式
        Paint.FontMetrics fm = paint.getFontMetrics();
        int textFontHeight = (int) Math.ceil(fm.descent - fm.top) + 2;// 计算字体高度座位图片高度
        int imageHotWidth = 0;
        int imageAgioWidth = 0;
        int imageWidth = 0;

        ImageSpan span1 = null;
        ImageSpan span2 = null;

        if (isHotCar) {
            drawableHot = context.getResources().getDrawable(R.drawable.ic_hot);
            imageHotWidth = drawableHot.getIntrinsicWidth() * textFontHeight
                    / drawableHot.getIntrinsicHeight();// 计算图片根据字体大小等比例缩放后的宽度
            drawableHot.setBounds(0, DensityUtils.dp2px(context, 1), imageHotWidth,
                    textFontHeight);// 缩放图片
            imageWidth = (int) (imageWidth + imageHotWidth + paint.measureText(" "));
            span1 = new ImageSpan(drawableHot, ImageSpan.ALIGN_BASELINE);
        }

        if (isAgioCar) {
            drawableAgio = context.getResources().getDrawable(R.drawable.ic_favorable);
            imageAgioWidth = drawableAgio.getIntrinsicWidth() * textFontHeight
                    / drawableAgio.getIntrinsicHeight();
            drawableAgio.setBounds(0, DensityUtils.dp2px(context, 1), imageAgioWidth,
                    textFontHeight);// 缩放图片
            imageWidth = (int) (imageWidth + imageAgioWidth + paint.measureText(" "));
            span2 = new ImageSpan(drawableAgio, ImageSpan.ALIGN_BASELINE);
        }

        int maxWidth = textView.getLayoutParams().width;// 获取文本控件最大宽度

        if (paint.measureText(content) > maxWidth) {// 如果文本大于一行才进入复杂的计算逻辑
            String s = "";// 临时存储截取的字符串
            int start = 0;// 截取的起始位置
            int end = 1;// 截取的结束位置
            int lines = 1;// 计算的行数
            boolean flag = false;// 已经计算到了最大行但是该行文本加图片的宽度超出文本框的宽度时，设置该标记将进行文本递减拼接测量
            do {
                s = content.substring(start, end);// 截取制定位置的字符串
                float width = paint.measureText(s);// 测量截取的字符串宽度
                if (width < maxWidth) {// 截取的文字长度小于控件宽度
                    if (lines == maxLines) {// 如果已经是最大行
                        if (width + imageWidth < maxWidth) {
                            // 文本宽度+图片宽度小于控件宽度
                            if (width + imageWidth + paint.measureText("...") < maxWidth) {
                                // 文本宽度+图片宽度+省略号宽度大于控件宽度
                                if (flag) {
                                    // 递减测量的第一次进入并且满足上一个if则停止循环
                                    content = content.substring(0, end);// 文案切割
                                    content += "...";// 文案拼接省略号
                                    break;
                                } else {// 还在进行递增测量，结束位置+1
                                    end++;
                                }
                            } else {
                                // 文本宽度+图片宽度+省略号宽度大于控件宽度，因为已经是最大行（lines ==
                                // maxWidth）需要对文字做递减测量，结束位置-1
                                end--;
                                flag = true;
                            }
                        } else {
                            // 文本宽度+图片宽度大于控件宽度，因为已经是最大行（lines == maxWidth）
                            // 需要对文字做递减测量，结束位置-1，flag置为true
                            end--;
                            flag = true;
                        }
                    } else {
                        // 截取文字的上限位置+1
                        end++;
                    }
                } else {
                    // 截取的文字长度大于控件宽度，一行的位置已经确定，下一行的起始位置为当前结束位置-1，行数+1
                    start = end - 1;
                    lines++;
                }

            } while (end <= content.length() && lines <= maxLines);
        }
        // 文本后面拼接图片
        if (isHotCar) {
            content += " *";
        }

        if (isAgioCar) {
            content += " *";
        }

        SpannableString spanString = new SpannableString(content);
        if (isAgioCar) {
            spanString.setSpan(span2, content.length() - 1, content.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (isHotCar) {
            if (isAgioCar) {
                spanString.setSpan(span1, content.length() - 3, content.length() - 2,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                spanString.setSpan(span1, content.length() - 1, content.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        textView.setText(spanString);
    }
}
