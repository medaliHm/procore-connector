$connectTestResult = Test-NetConnection -ComputerName csb100320011d345519.file.core.windows.net -Port 445
if ($connectTestResult.TcpTestSucceeded) {
    # Save the password so the drive will persist on reboot
    cmd.exe /C "cmdkey /add:`"csb100320011d345519.file.core.windows.net`" /user:`"Azure\csb100320011d345519`" /pass:`"TsVDIQkkGAPxH0e0wdEQx8OSu3TWv56xvJwLX8F/2RuvO2F/JDnnL9XnA36GLD9PzKZ3zgMXWDTQUXwztkgUtQ==`""
    # Mount the drive
    New-PSDrive -Name Z -PSProvider FileSystem -Root "\\csb100320011d345519.file.core.windows.net\share-procore" -Persist
} else {
    Write-Error -Message "Unable to reach the Azure storage account via port 445. Check to make sure your organization or ISP is not blocking port 445, or use Azure P2S VPN, Azure S2S VPN, or Express Route to tunnel SMB traffic over a different port."
}