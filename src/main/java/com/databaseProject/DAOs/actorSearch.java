public List<Media> getMovieWithActor(String actor, boolean ifWonAwards, boolean isPreviouslyRented, String userEmail)
{
    List<Media>           actorSearchList;
    List<Integer>         listOfMedia;
    PreparedStatement     pstatement;
    ResultSet             resultSet;

    actorSearchList  = new ArrayList<Media>();
    listOfMedia = new ArrayList<Media>;
    Media        media;
    pstatement   = null;
    resultSet    = null;


    try
    {
        Connection connection = ConnectionManager.getConnection();
        if (isPreviouslyRented && !ifWonAwards)
        {
            pstatement = connection.prepareStatement("SELECT FROM Worker W, Works_On WO, Media M Won WN WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ? AND M.mediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
            pState.clearParameters();
            pState.setString(1, actor);
            pState.setString(2, userEmail);
        }

        else if (ifWonAwards && !isPreviouslyRented)
        {
            pstatement = connection.prepareStatement("SELECT FROM Worker W, Works_On WO, Media M Won WN WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ? AND WN.movieID = M.mediaID");
            pState.clearParameters();
            pState.setString(1, actor);
        }

        else if (isPreviouslyRented && ifWonAwards)
        {
            pstatement = connection.prepareStatement("SELECT FROM Worker W, Works_On WO, Media M Won WN WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ? AND WN.movieID = M.mediaID AND M.MediaID NOT IN (SELECT mediaID FROM rental_info WHERE email = ?)");
            pState.clearParameters();
            pState.setString(1, actor);
            pState.setString(2, userEmail);
        }

        else
        {
            pstatement = connection.prepareStatement("SELECT FROM Worker W, Works_On WO, Media M Won WN WHERE W.isActor = ? AND W.workerID = WO.workerID AND W.wname = ?");
            pstatement.clearParameters();
            pstatement.setString(1, actor);
        }


            pstatement.clearParameters();
            pstatement.setString(1, actor);

            resultSet = pstatement.executeQuery();

            while (resultSet.next())
            {
                listOfMedia.add(resultSet.getInt("mediaID"));

            } //end while

            resultSet.close();
            pstatement.close();
            connection.close();

            actorSearchList = getMedia(listOfMedia);

            return actorSearchList;

    } // end of try

    catch(SQLException sqle)
    {

        System.out.println("SQLState = " + sqle.getSQLState() + "\n" + sqle.getMessage());
        return actorSearchList;
    } // end of catch



}
