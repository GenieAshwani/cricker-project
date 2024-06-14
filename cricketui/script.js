document.addEventListener('DOMContentLoaded', function() {
    const liveScoreBtn = document.querySelector('.live-score-btn');
    const liveScoreContainer = document.querySelector('.live-score-container');

    liveScoreBtn.addEventListener('click', function() {
        // Hide the live score button
        liveScoreBtn.style.display = 'none';

        // Fetch live score immediately
        fetchLiveScore();

        // Set interval to fetch live score every 2 seconds (2000 milliseconds)
        setInterval(fetchLiveScore, 2000);
    });

    function fetchLiveScore() {
        fetch('http://localhost:8080/cricket/live-score')
            .then(response => response.json())
            .then(data => {
                // Update the live score container with fetched data
                updateLiveScore(data);
            })
            .catch(error => {
                console.error('Error fetching live score:', error);
                // Handle error as needed, e.g., show an error message
            });
    }

    function updateLiveScore(scoreData) {
        const { run, out, over } = scoreData;

        // Construct HTML for the live score display
        const html = `
            <div class="live-score">
                <p>Runs: ${run}</p>
                <p>Wickets: ${out}</p>
                <p>Overs: ${over}</p>
            </div>
        `;

        // Update the live score container with the new HTML
        liveScoreContainer.innerHTML = html;
    }
});
