# HealthWatch

Health watch 1st phase

Application capability

-Continuously record HR
-In a fixed period, collect N point of sampled data in time domain

Required HRV bandwidth according to paper = 0.5 Hz
Sampling frequency = 0.5*2 = 1 Hz

Required frequency bin (ช่องความถี่ที่ย่อยที่สุดที่ต้องการเวลาพล๊อตfrequency domain) = 0.005 Hz
=> Required BW/(N/2) = Required frequency bin
=> N = Required BW/Required frequency bin*2
=> N = 0.5/0.005*2 = 200 => rounded to next FFT size => N = 256

ดังนั้นต้องตัดข้อมูลทุกๆ N/sampling frequency = 256 s (4 นาที 16 วินาที)

-Extract features from N points data
 => time domain
 => frequency domain

-Interpret from extracted feature
