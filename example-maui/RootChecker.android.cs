using Android.App;
using Com.Security.Library;

public static class SecurityChecker {
    public static bool IsRooted() => SecurityChecks.IsRooted();
    public static bool IsMagiskPresent() => SecurityChecks.IsMagiskPresent();
    public static bool IsEmulator() => SecurityChecks.IsEmulator();
    public static bool IsDebuggerConnected() => SecurityChecks.IsDebuggerConnected();
    public static bool HasFridaOrXposed() => SecurityChecks.HasFridaOrXposed();

    public static void RequestIntegrityToken(Activity activity, string nonce, Action<string?> callback) =>
        SecurityChecks.RequestIntegrityToken(activity, nonce, callback);
}
