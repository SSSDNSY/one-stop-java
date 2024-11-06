package reactor.callbackhell;

/**
 * @Description
 * @Since 2024-11-06
 * @Copyright ©OPPEIN HOME GROUP INC.All Rights Reserved
 */
public class HellTest {

    public static void main(String[] args) {

    }
//    public void onSuccess(List<String> list) {
//        if (list.isEmpty()) {
//            suggestionService.getSuggestions(new Callback<List<Favorite>>() {
//                public void onSuccess(List<Favorite> list) {
//                    UiUtils.submitOnUiThread(() -> {
//                        list.stream()
//                                .limit(5)
//                                .forEach(uiList::show);
//                    });
//                }
//
//                public void onError(Throwable error) {
//                    UiUtils.errorPopup(error);
//                }
//            });
//        } else {
//            list.stream()
//                    .limit(5)
//                    .forEach(favId -> favoriteService.getDetails(favId,
//                            new Callback<Favorite>() {
//                                public void onSuccess(Favorite details) {
//                                    UiUtils.submitOnUiThread(() -> uiList.show(details));
//                                }
//
//                                public void onError(Throwable error) {
//                                    UiUtils.errorPopup(error);
//                                }
//                            }
//                    ));
//        }
//    }
//
//    public void onError(Throwable error) {
//        UiUtils.errorPopup(error);
//    }
//});

}
