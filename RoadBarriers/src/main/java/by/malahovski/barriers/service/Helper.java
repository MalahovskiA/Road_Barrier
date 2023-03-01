package by.malahovski.barriers.service;


//public class Helper implements Runnable {
//
////    private UserEntity userEntity;
////
////    public Helper(UserEntity userEntity) {
////        this.userEntity = userEntity;
////    }
////
////
////    @Override
////    public void run() {
////        System.out.print("Поток с именем " + Thread.currentThread().getName() + " запущен");
////        System.out.println(userEntity.getLogin());
////        System.out.println(userEntity.getRoleEntity());
////
////
////        Path newFilePath = Paths.get("D:", "LOG.txt");
////
////        if (!Files.exists(newFilePath)) {
////            try {
////                Files.createFile(newFilePath);
////            } catch (IOException e) {
////                System.err.println(e);
////            }
////        }
//////        try (BufferedWriter bw = Files.newBufferedWriter(newFilePath, StandardCharsets.UTF_8)) {
//////            bw.write(userEntity.getLoginName());
//////            bw.write(userEntity.getEmail());
//////            System.out.println("Запись файла успешна");
////        try {
////            Files.writeString(newFilePath, userEntity.toString(), StandardOpenOption.APPEND);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//}
