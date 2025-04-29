function searchWord() {
    const word = document.getElementById("wordInput").value.trim();
    if (word === "") {
        document.getElementById("result").innerHTML = "Please enter a word.";
        return;
    }

    fetch(`/api/definition?word=${word}`)
        .then(response => response.json())
        .then(data => {
            console.log(data); // for debugging

            if (data && data.length > 0) {
                let meaning = data[0].shortdef ? data[0].shortdef.join(', ') : "No short definition found.";
                let partOfSpeech = data[0].fl || "N/A";

                document.getElementById("result").innerHTML = `
                    <h2>Word: ${word}</h2>
                    <p><strong>Part of Speech:</strong> ${partOfSpeech}</p>
                    <p><strong>Definition:</strong> ${meaning}</p>
                `;
            } else {
                document.getElementById("result").innerHTML = "No definition found.";
            }
        })
        .catch(error => {
            console.error(error);
            document.getElementById("result").innerHTML = "Error fetching definition.";
        });
}
