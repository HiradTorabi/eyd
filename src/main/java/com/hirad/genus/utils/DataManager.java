/*package com.hirad.genus.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hirad.genus.seed.SeedData;
import java.io.*;

public class DataManager {
    private static final String DATA_FILE = "data.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // ذخیره همه داده‌ها در فایل JSON
    public static void saveAllData() {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            gson.toJson(SeedData.class, writer);
            System.out.println("✅ داده‌ها ذخیره شدند!");
        } catch (IOException e) {
            System.out.println("❌ خطا در ذخیره داده‌ها: " + e.getMessage());
        }
    }

    // بارگذاری داده‌ها از فایل JSON
    public static void loadAllData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (FileReader reader = new FileReader(DATA_FILE)) {
                SeedData data = gson.fromJson(reader, SeedData.class);

                // کپی داده‌های بارگذاری شده به SeedData اصلی
                SeedData.users = data.users;
                SeedData.artists = data.artists;
                SeedData.admins = data.admins;
                SeedData.songs = data.songs;
                SeedData.albums = data.albums;

                System.out.println("✅ داده‌ها با موفقیت بارگذاری شدند!");
            } catch (IOException e) {
                System.out.println("❌ خطا در بارگذاری داده‌ها! از داده‌های پیش‌فرض استفاده می‌شود.");
                SeedData.generate();
            }
        } else {
            System.out.println("⚠️ فایل داده وجود ندارد! داده‌های پیش‌فرض ساخته شدند.");
            SeedData.generate();
        }
    }
}*/