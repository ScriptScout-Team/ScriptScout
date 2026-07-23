@echo off
echo ========================================================
echo ScriptScout AI: Starting local FastAPI App on Port 8000
echo ========================================================
start "ScriptScout AI Server" cmd /k "python -m uvicorn app:app --host 0.0.0.0 --port 8000"

echo Waiting 5 seconds for local server to spin up...
timeout /t 5 > nul

echo ========================================================
echo ScriptScout AI: Creating secure public HTTPS URL...
echo ========================================================
echo Copy the HTTPS URL displayed below and paste it into your 
echo Spring Boot application.properties under: ai.service.url
echo ========================================================
ssh -R 80:localhost:8000 nokey@localhost.run
