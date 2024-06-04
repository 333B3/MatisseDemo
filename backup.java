 private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/test.dat"))) {
            String line = br.readLine();
            int numClients = Integer.parseInt(line.trim());

            for (int i = 0; i < numClients; i++) {
                String clientInfo = br.readLine().trim();
                String[] clientData = clientInfo.split("\\s+"); // Розділити рядок за будь-якою кількістю пробілів

                if (clientData.length >= 3) { // Перевірка на кількість елементів у масиві
                    String firstName = clientData[0];
                    String lastName = clientData[1];
                    int numAccounts = Integer.parseInt(clientData[2]);

                    Client client = new Client(firstName, lastName);
                    for (int j = 0; j < numAccounts; j++) {
                        line = br.readLine().trim();
                        String[] accountData = line.split("\\s+"); // Розділити рядок за будь-якою кількістю пробілів

                        if (accountData.length >= 3) { // Перевірка на кількість елементів у масиві
                            String type = accountData[0];
                            double balance = Double.parseDouble(accountData[1]);
                            double additionalInfo = Double.parseDouble(accountData[2]);

                            if (type.equals("S")) {
                                client.addAccount(new SavingsAccount(balance, additionalInfo));
                            } else if (type.equals("C")) {
                                client.addAccount(new CheckingAccount(balance, additionalInfo));
                            }
                        }
                    }
                    clients.put(firstName + " " + lastName, client);
                    comboBox1.addItem(firstName + " " + lastName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error parsing data file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }