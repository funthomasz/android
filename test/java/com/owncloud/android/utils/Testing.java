package com.owncloud.android.utils;

import android.content.Context;
import com.owncloud.android.AppRater;
import com.owncloud.android.R;
import com.owncloud.android.db.UploadResult;
import com.owncloud.android.lib.common.operations.RemoteOperationResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by ballu on 16-Nov-18. Do NOT modify/duplicate/upload/download/see/remember this code (or any part of it)
 * without the permission of the owner (a.k.a. me)! Any violation of these rules will be taken care of.
 * By interacting with this code you agree to these statements and conditions.
 */
@RunWith(MockitoJUnitRunner.class)
public class Testing{

    @Mock
   private Context context = mock(Context.class);

    @Test
    public void testDays(){
        assertThat(AppRater.daysToMilliseconds(0)==0).isTrue();
        assertThat(AppRater.daysToMilliseconds(1)==86400000).isTrue();
        assertThat(AppRater.daysToMilliseconds(Integer.parseInt("10"))==864000000).isTrue();
        assertThat(AppRater.daysToMilliseconds(-5)).isLessThan(0);
        assertThat(AppRater.daysToMilliseconds(-2)==-172800000).isTrue();
        assertThat(AppRater.daysToMilliseconds(Integer.MAX_VALUE)==Integer.MAX_VALUE).isFalse();
    }

    @Test
    public void testConnection(){
        assertThat(ConnectivityUtils.isAppConnectedViaWiFi(context)).isFalse();
    }

    @Test
    public void testBitmap(){
        assertThat(BitmapUtils.HSLtoRGB(250.0f, 50.0f, 35.0f, 1f)).isNotNull();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(0.0f, 0.0f, 0.0f, 0f), new int[]{0, 0, 0})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(590.0f, 0.0f, 0.0f, 1f), new int[]{0, 0, 0})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(-590.485f, 0.0f, 0.0f, 0f), new int[]{0, 0, 0})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(0.0f, 0.0f, 99.0f, 1f), new int[]{253, 253, 253})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(355.0f, 10.0f, 5.0f, 0.651f), new int[]{14, 12, 12})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(180.0f, 88.0f, 15.0f, 0.88f), new int[]{5, 72, 72})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(355.0f, 25.0f, 5.0f, 1.0f), new int[]{16, 10, 10})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(355.0f, 25.0f, 51.0f, 0.5f), new int[]{162, 99, 104})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(215.0f, 62.0f, 34.0f, 1.0f), new int[]{33, 78, 141})).isTrue();
        assertThat(Arrays.equals(BitmapUtils.HSLtoRGB(355.0f, 85.0f, 98.0f, 0.6851f), new int[]{255, 247, 247})).isTrue();
    }

    @Test
    public void testDate(){
        Date testDate = new Date(2018,5,12);
        assertThat(DateUtils.addDaysToDate(testDate, 0).getTime() == testDate.getTime()).isTrue();
        assertThat(DateUtils.addDaysToDate(testDate, 1).getTime() == testDate.getTime()).isNotNull();
        assertThat(DateUtils.addDaysToDate(testDate, 1).getTime() == testDate.getTime()).isFalse();
        assertThat(DateUtils.addDaysToDate(DateUtils.addDaysToDate(testDate,1), -1).getTime() ==
                DateUtils.addDaysToDate(DateUtils.addDaysToDate(testDate,-1), 1).getTime()).isTrue();
        assertThat(DateUtils.addDaysToDate(testDate, 1).getTime() == new Date(2018,5,13).getTime()).isTrue();
        assertThat(DateUtils.addDaysToDate(testDate, -1).getTime() == new Date(2018,5,11).getTime()).isTrue();
        assertThat(DateUtils.addDaysToDate(testDate, 365).getTime() == new Date(2019,5,12).getTime()).isTrue();
        assertThat(DateUtils.addDaysToDate(testDate, 2192).getTime() == new Date(2024,5,12).getTime()).isTrue();
    }

    @Test
    public void testDisplay(){
        String unknown = "Unknown type";
        String image1 = "image/jpeg";
        String image2 = "image/tiff";
        String image3 = "image/png";
        String image4 = "image/gif";
        String audio1 = "audio/mpeg";
        String audio2 = "application/ogg";
        String image1readable = "JPEG image";
        String image2readable = "TIFF image";
        String image3readable = "PNG image";
        String image4readable = "GIF image";
        String audio1readable = "MP3 music file";
        String audio2readable = "OGG music file";
        String slash1 = "a string with a slash/";
        String slash2 = "a string with two slashes//";
        String slash3 = "a string with a backslash\\";
        String slash4 = "a string without ending slash/ ";
        Date date1 = new Date(2018,12,5,20,0,0);
        Date date2 = new Date(1995,9,27,1,0,0);
        Date date3 = Calendar.getInstance().getTime();
        DateFormat df = DateFormat.getDateTimeInstance();

        assertThat(DisplayUtils.convertMIMEtoPrettyPrint("something unknown").equals(unknown)).isTrue();
        assertThat(DisplayUtils.convertMIMEtoPrettyPrint(image1).equals(image1readable)).isTrue();
        assertThat(DisplayUtils.convertMIMEtoPrettyPrint(image2).equals(image2readable)).isTrue();
        assertThat(DisplayUtils.convertMIMEtoPrettyPrint(image3).equals(image3readable)).isTrue();
        assertThat(DisplayUtils.convertMIMEtoPrettyPrint(image4).equals(image4readable)).isTrue();
        assertThat(DisplayUtils.convertMIMEtoPrettyPrint(audio1).equals(audio1readable)).isTrue();
        assertThat(DisplayUtils.convertMIMEtoPrettyPrint(audio2).equals(audio2readable)).isTrue();

        assertThat(DisplayUtils.getPathWithoutLastSlash(slash1).equals(slash1.substring(0, slash1.length()-1))).isTrue();
        assertThat(DisplayUtils.getPathWithoutLastSlash(slash2).equals(slash2.substring(0, slash2.length()-1))).isTrue();
        assertThat(DisplayUtils.getPathWithoutLastSlash(slash3).equals(slash3)).isTrue();
        assertThat(DisplayUtils.getPathWithoutLastSlash(slash4).equals(slash4)).isTrue();

        assertThat(DisplayUtils.unixTimeToHumanReadable(date1.getTime())).isNotNull();
        assertThat(DisplayUtils.unixTimeToHumanReadable(date1.getTime())).isInstanceOf(String.class);
        assertThat(DisplayUtils.unixTimeToHumanReadable(date1.getTime()).equals(df.format(date1))).isTrue();
        assertThat(DisplayUtils.unixTimeToHumanReadable(date2.getTime()).equals(df.format(date2))).isTrue();
        assertThat(DisplayUtils.unixTimeToHumanReadable(date3.getTime()).equals(df.format(date3))).isTrue();
    }

    @Test
    public void testDisplayBites(){
        long kilobyte = 1024;
        long megabyte = 1024*kilobyte;
        long gigabyte = 1024*megabyte;
        long terabite = 1024*gigabyte;
        long petabite = 1024*terabite;
        long exabyte = 1024*petabite;

        assertThat(DisplayUtils.bytesToHumanReadable(0, context).equals("0 B")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(kilobyte, context).equals("1 KB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(megabyte, context).equals("1 MB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(gigabyte, context).equals("1 GB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(terabite, context).equals("1 TB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(petabite, context).equals("1 PB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(exabyte, context).equals("1 EB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(Long.MAX_VALUE, context).equals("8 EB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(megabyte/kilobyte/1024, context).equals("1 B")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(5*megabyte, context).equals("5 MB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable(128*gigabyte, context).equals("128 GB")).isTrue();
        assertThat(DisplayUtils.bytesToHumanReadable( 666 *terabite / megabyte, context).equals("666 MB")).isTrue();
    }

    @Test
    public void testFileStorage(){
        String string1 = "ee\\ma\\ka\\re\\na";
        String string1backSlash = "ee\\ma\\ka\\re\\na\\";
        String string1Parent = "ee\\ma\\ka\\re\\";
        String string2 = "root\\system";
        String string2backSlash = "root\\system\\";
        String string2Parent = "root\\";

        assertThat(FileStorageUtils.getParentPath(string1)).isNotNull();
        assertThat(FileStorageUtils.getParentPath(string2)).isNotNull();
        assertThat(FileStorageUtils.getParentPath(string1)).isNotEmpty();
        assertThat(FileStorageUtils.getParentPath(string2)).isNotEmpty();
        assertThat(FileStorageUtils.getParentPath(string1)).isInstanceOf(String.class);
        assertThat(FileStorageUtils.getParentPath(string1).equals(string1Parent)).isTrue();
        assertThat(FileStorageUtils.getParentPath(string1backSlash).equals(string1Parent)).isTrue();
        assertThat(FileStorageUtils.getParentPath(string2).equals(string2Parent)).isTrue();
        assertThat(FileStorageUtils.getParentPath(string2backSlash).equals(string2Parent)).isTrue();
    }

    @Test
    public void testMime(){
        String video = "video.mp4";
        String image1 = "image.jpg";
        String image2 = "image.png";
        String image3 = "image.tiff";
        String image4 = "image.jpeg";
        String image5 = "image.svg";
        String text1 = "text.txt";
        String text2 = "text.xml";
        String code1 = "code.html";
        String code2 = "code.js";
        String code3 = "code.css";
        String code4 = "code.php";

        assertThat(MimetypeIconUtil.getFileTypeIconId(null, video)).isNotNull();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, video) == R.drawable.file_movie).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, image1) == R.drawable.file_image).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, image2) == R.drawable.file_image).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, image3) == R.drawable.file_image).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, image4) == R.drawable.file_image).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, image5) == R.drawable.file_image).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, text1) == R.drawable.file_text).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, text2) == R.drawable.file_text).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, code1) == R.drawable.file_code).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, code2) == R.drawable.file_code).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, code3) == R.drawable.file_code).isTrue();
        assertThat(MimetypeIconUtil.getFileTypeIconId(null, code4) == R.drawable.file_code).isTrue();

        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(video)).isNotNull();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(code2)).isNotNull();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(image3)).isNotNull();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(video).equals("video/mp4")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(code1).equals("text/html")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(code2).equals("application/javascript")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(code3).equals("text/css")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(code4).equals("application/x-php")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(image1).equals("image/jpeg")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(image2).equals("image/png")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(image3).equals("image/tiff")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(image4).equals("image/jpeg")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(text1).equals("text/plain")).isTrue();
        assertThat(MimetypeIconUtil.getBestMimeTypeByFilename(text2).equals("application/xml")).isTrue();

        assertThat(MimetypeIconUtil.getFolderTypeIconId(false,false) == R.drawable.ic_menu_archive).isTrue();
        assertThat(MimetypeIconUtil.getFolderTypeIconId(false,true) == R.drawable.folder_public).isTrue();
        assertThat(MimetypeIconUtil.getFolderTypeIconId(true,false) == R.drawable.shared_with_me_folder).isTrue();
        assertThat(MimetypeIconUtil.getFolderTypeIconId(true,true) == R.drawable.folder_public).isTrue();
    }

    @Test
    public void testPower(){
        assertThat(PowerUtils.isDeviceIdle(context)).isNotNull();
        assertThat(PowerUtils.isDeviceIdle(context)).isInstanceOf(Boolean.class);
        assertThat(PowerUtils.isDeviceIdle(context)).isFalse();
        assertThat(PowerUtils.isDeviceIdle(null)).isNotNull();
        assertThat(PowerUtils.isDeviceIdle(null)).isFalse();

        System.out.println(SecurityUtils.stringToMD5Hash("daso"));
    }

    @Test
    public void testSecurity(){
        String string1 = "This is a string.";
        String string2 = "This is a string..";
        String string3 = "Completely different set of characters.";
        String string4 = "\n";
        String string5 = " ";
        String string6 = "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862";
        String string1Hash = "13562B471182311B6EEA8D241103E8F0".toLowerCase();
        String string2Hash = "229260BFDEAB2D12C01A65A768DD47D4".toLowerCase();
        String string3Hash = "2AE11D87E6F3E1C60880A8F3097BBE81".toLowerCase();
        String string4Hash = "68b329da9893e34099c7d8ad5cb9c940".toLowerCase();
        String string5Hash = "7215EE9C7D9DC229D2921A40E899EC5F".toLowerCase();
        String string6Hash = "E6322458CA85F7D7F4A94AA17F1F5087".toLowerCase();

        assertThat(SecurityUtils.stringToMD5Hash(string1)).isNotNull();
        assertThat(SecurityUtils.stringToMD5Hash(string2)).isNotEmpty();
        assertThat(SecurityUtils.stringToMD5Hash(string3)).isInstanceOf(String.class);
        assertThat(SecurityUtils.stringToMD5Hash(string4)).isNotNull();
        assertThat(SecurityUtils.stringToMD5Hash(string5)).isNotEmpty();
        assertThat(SecurityUtils.stringToMD5Hash(string6)).isInstanceOf(String.class);
        assertThat(SecurityUtils.stringToMD5Hash(string1).equals(string1Hash)).isTrue();
        assertThat(SecurityUtils.stringToMD5Hash(string2).equals(string2Hash)).isTrue();
        assertThat(SecurityUtils.stringToMD5Hash(string3).equals(string3Hash)).isTrue();
        assertThat(SecurityUtils.stringToMD5Hash(string4).equals(string4Hash)).isTrue();
        assertThat(SecurityUtils.stringToMD5Hash(string5).equals(string5Hash)).isTrue();
        assertThat(SecurityUtils.stringToMD5Hash(string6).equals(string6Hash)).isTrue();
    }

    @Test
    public void testUploadResult(){
        RemoteOperationResult remoteOperationResult1 = new RemoteOperationResult(RemoteOperationResult.ResultCode.OK);
        RemoteOperationResult remoteOperationResult2 = new RemoteOperationResult(RemoteOperationResult.ResultCode.NO_NETWORK_CONNECTION);
        RemoteOperationResult remoteOperationResult3 = new RemoteOperationResult(RemoteOperationResult.ResultCode.TIMEOUT);
        RemoteOperationResult remoteOperationResult4 = new RemoteOperationResult(RemoteOperationResult.ResultCode.UNKNOWN_ERROR);
        RemoteOperationResult remoteOperationResult5 = new RemoteOperationResult(RemoteOperationResult.ResultCode.DELAYED_FOR_WIFI);
        RemoteOperationResult remoteOperationResult6 = new RemoteOperationResult(RemoteOperationResult.ResultCode.LOCAL_FILE_NOT_FOUND);
        RemoteOperationResult remoteOperationResult7 = new RemoteOperationResult(RemoteOperationResult.ResultCode.UNAUTHORIZED);
        RemoteOperationResult remoteOperationResult8 = new RemoteOperationResult(RemoteOperationResult.ResultCode.SSL_ERROR);

        assertThat(UploadResult.fromValue(1)).isNotNull();
        assertThat(UploadResult.fromValue(-1)).isInstanceOf(UploadResult.class);
        assertThat(UploadResult.fromValue(1)).isInstanceOf(UploadResult.class);
        assertThat(UploadResult.fromValue(16)).isInstanceOf(UploadResult.class);
        assertThat(UploadResult.fromValue(1)).isAtLeast(UploadResult.UNKNOWN);
        assertThat(UploadResult.fromValue(1)).isAtMost(UploadResult.SPECIFIC_UNSUPPORTED_MEDIA_TYPE);
        assertThat(UploadResult.fromValue(-2)).isNull();
        assertThat(UploadResult.fromValue(-20)).isNull();
        assertThat(UploadResult.fromValue(17)).isNull();
        assertThat(UploadResult.fromValue(99)).isNull();
        assertThat(UploadResult.fromValue(-1)).isEquivalentAccordingToCompareTo(UploadResult.UNKNOWN);
        assertThat(UploadResult.fromValue(0)).isEquivalentAccordingToCompareTo(UploadResult.UPLOADED);
        assertThat(UploadResult.fromValue(1)).isEquivalentAccordingToCompareTo(UploadResult.NETWORK_CONNECTION);
        assertThat(UploadResult.fromValue(6)).isEquivalentAccordingToCompareTo(UploadResult.PRIVILEDGES_ERROR);
        assertThat(UploadResult.fromValue(7)).isEquivalentAccordingToCompareTo(UploadResult.CANCELLED);
        assertThat(UploadResult.fromValue(11)).isEquivalentAccordingToCompareTo(UploadResult.SERVICE_UNAVAILABLE);
        assertThat(UploadResult.fromValue(13)).isEquivalentAccordingToCompareTo(UploadResult.SSL_RECOVERABLE_PEER_UNVERIFIED);
        assertThat(UploadResult.fromValue(15)).isEquivalentAccordingToCompareTo(UploadResult.SPECIFIC_SERVICE_UNAVAILABLE);

        assertThat(UploadResult.fromOperationResult(remoteOperationResult1)).isNotNull();
        assertThat(UploadResult.fromOperationResult(remoteOperationResult1)).isInstanceOf(UploadResult.class);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult1)).isAtLeast(UploadResult.UNKNOWN);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult1)).isAtMost(UploadResult.SPECIFIC_UNSUPPORTED_MEDIA_TYPE);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult2)).isEquivalentAccordingToCompareTo(UploadResult.fromOperationResult(remoteOperationResult3));
        assertThat(UploadResult.fromOperationResult(remoteOperationResult3)).isEquivalentAccordingToCompareTo(UploadResult.NETWORK_CONNECTION);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult4)).isEquivalentAccordingToCompareTo(UploadResult.UNKNOWN);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult5)).isEquivalentAccordingToCompareTo(UploadResult.DELAYED_FOR_WIFI);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult6)).isEquivalentAccordingToCompareTo(UploadResult.FILE_NOT_FOUND);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult7)).isEquivalentAccordingToCompareTo(UploadResult.CREDENTIAL_ERROR);
        assertThat(UploadResult.fromOperationResult(remoteOperationResult8)).isEquivalentAccordingToCompareTo(UploadResult.fromOperationResult(remoteOperationResult2));
    }

}
