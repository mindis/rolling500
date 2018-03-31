package nl.gridshore.rolling500.searchstats;

import nl.gridshore.rolling500.albums.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Service used to log queries and clicks to a file
 */
@Service
public class SearchStatsService {
    private static final Logger QUERY_LOGGER = LoggerFactory.getLogger("QUERY_LOGGER");
    private static final Logger CLICK_LOGGER = LoggerFactory.getLogger("CLICKS_LOGGER");
    private static final Logger STATS_LOGGER = LoggerFactory.getLogger("STATS_LOGGER");

    public void logSearchStats(SearchRequest request, List<String> foundIds, long totalHits, String queryId,
                               String userId) {
        List<String> stats = Arrays.asList(
                String.valueOf(System.currentTimeMillis()),
                userId,
                queryId,
                String.valueOf(totalHits),
                request.getSearchString(),
                String.valueOf(request.getPage()),
                String.valueOf(request.getSize()),
                StringUtils.arrayToDelimitedString(foundIds.toArray(), ";")
        );

        if (QUERY_LOGGER.isInfoEnabled()) {
            QUERY_LOGGER.info(StringUtils.arrayToDelimitedString(stats.toArray(), "#"));
        }
    }

    public void logClickStat(String queryId, long albumId, String userId) {
        List<String> stats = Arrays.asList(
                String.valueOf(System.currentTimeMillis()),
                userId,
                queryId,
                String.valueOf(albumId)
        );
        if (CLICK_LOGGER.isInfoEnabled()) {
            CLICK_LOGGER.info(StringUtils.arrayToDelimitedString(stats.toArray(), "#"));
        }
    }

    public void logStats(String text) {
        if (STATS_LOGGER.isInfoEnabled()) {
            STATS_LOGGER.info(text);
        }
    }

}
