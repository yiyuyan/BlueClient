package cn.ksmcbrigade.bc.utils;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.system.Platform;

import static com.mojang.text2speech.Narrator.LOGGER;

public class DarkUtils {
    public static void setDark(Window w) {
        if (Platform.get() != Platform.WINDOWS) {
            LOGGER.warn("The DarkWindowTitle feature only works on windows!");
            return;
        }

        WinNT.OSVERSIONINFO osversioninfo = new WinNT.OSVERSIONINFO();

        Kernel32.INSTANCE.GetVersionEx(osversioninfo);

        if (osversioninfo.dwMajorVersion.longValue() < 10 || osversioninfo.dwBuildNumber.longValue() < 17763) { // 1809
            LOGGER.warn("The DarkWindowTitle feature requires Windows 10 version 1809 or newer!");
            return;
        }

        long glfwWindow = w.getHandle();
        long hwndLong = GLFWNativeWin32.glfwGetWin32Window(glfwWindow);
        WinDef.HWND hwnd = new WinDef.HWND(Pointer.createConstant(hwndLong));

        Memory mem = new Memory(Native.POINTER_SIZE);
        mem.setInt(0, 1);
        DwmApi.INSTANCE.DwmSetWindowAttribute(
                hwnd,
                DwmApi.DWMWA_USE_IMMERSIVE_DARK_MODE,
                new WinDef.LPVOID(mem),
                new WinDef.DWORD(WinDef.DWORD.SIZE)
        );
        mem.close();

        int oldWidth = w.getWidth();
        w.setWindowedSize(oldWidth + 2, w.getHeight());
        w.setWindowedSize(oldWidth, w.getHeight());
    }

    public interface DwmApi extends StdCallLibrary {

        DwmApi INSTANCE = Native.load("dwmapi", DwmApi.class, W32APIOptions.DEFAULT_OPTIONS);

        WinDef.DWORD DWMWA_USE_IMMERSIVE_DARK_MODE = new WinDef.DWORD(20);

        @SuppressWarnings("UnusedReturnValue")
        WinNT.HRESULT DwmSetWindowAttribute(
                WinDef.HWND hwnd,
                WinDef.DWORD dwAttribute,
                WinDef.LPVOID pvAttribute,
                WinDef.DWORD cbAttribute
        );

    }
}
