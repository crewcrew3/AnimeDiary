package itis.animediary.utils;

import java.util.Arrays;
import java.util.List;

public class CarouselListSingleton {

    private static List<String> banners;

    public static List<String> getBanners() {
        if (banners == null) {
            banners = Arrays.asList(
                    "https://i.pinimg.com/originals/00/a2/52/00a252515f3b610febf40bb0fc1f04df.jpg", //персона
                    "https://i.pinimg.com/736x/54/03/0f/54030fc0f818843e20df2935e2bfe035.jpg", //тоторо
                    "https://i.pinimg.com/736x/16/7a/5b/167a5bd064ef4ff5aaaf613e6654f9b6.jpg", //гиас
                    "https://i.pinimg.com/736x/fe/99/55/fe99552c6b5b63b9f10c48f101ec1979.jpg", //хаяо
                    "https://i.pinimg.com/736x/5c/8f/ed/5c8fed13386d5bcded547af787c3c694.jpg", //ходячий замок2
                    "https://avatars.mds.yandex.net/get-mpic/6277643/img_id4521686969756684495.jpeg/orig", //пандора2
                    "https://avatars.mds.yandex.net/i?id=42624f0f29be06af8e8a71ec80266c15_l-5875719-images-thumbs&n=13", //врата штейна
                    "https://avatars.mds.yandex.net/i?id=891e456b4dbadd2d03b183d028e003cc_l-5690841-images-thumbs&n=13", //атака титанов2
                    "https://i.pinimg.com/736x/5b/76/51/5b7651da9df4b8935f1339fd37adeaaf.jpg", //ходячий замок3
                    "https://avatars.mds.yandex.net/i?id=49f4a5fbf80570519c63f653bd81cdaa_l-5139423-images-thumbs&n=13", //магичка
                    "https://i.pinimg.com/736x/78/2e/ef/782eefb81c68d65d5efc2037a552ca49.jpg", //неверленд3
                    "https://i.pinimg.com/736x/d8/b5/72/d8b572ee14664ec48eb23c696b11b33f.jpg", //дворецкий
                    "https://avatars.mds.yandex.net/get-mpic/5272194/img_id395326522426495576.jpeg/orig", //атака титанов3
                    "https://i.pinimg.com/736x/e1/23/75/e123759111b44302803a371ac1a26c60.jpg", //унесенные призраками
                    "https://kg-portal.ru/img/68959/main_2x.jpg", //врата штейна2
                    "https://i.pinimg.com/736x/ee/b5/dc/eeb5dcc835891c09dfcf476391baab57.jpg", //маомао
                    "https://i.pinimg.com/736x/60/94/21/6094215e75ee9b1532d2acc7ec4397e3.jpg", //клинок
                    "https://i.pinimg.com/originals/a3/5e/4f/a35e4fd3f9873eea99c9fc2ae065ed5b.jpg" //дороро1
            );
        }
        return banners;
    }
}
