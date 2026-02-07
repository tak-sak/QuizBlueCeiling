document.getElementById('quiz-form').addEventListener('submit', function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    if (e.submitter) {
        formData.append('choice', e.submitter.value);
    }

    fetch('/answer', {
        method: 'POST',
        body: formData
    })
    .then(res => {
        if (!res.ok) throw new Error('Network response was not ok');
        return res.json();
    })
    .then(data => {
        const overlay = document.getElementById('effect-overlay');
        const resultImg = document.getElementById('result-image');

        resultImg.src = data.correct ? '/images/correct.png' : '/images/wrong.png';
        overlay.style.display = 'flex';

        if (!data.correct) {
            document.body.classList.add('invert');
        }

        setTimeout(() => {
            document.body.classList.remove('invert');
            if (data.nextIndex === -1) {
                window.location.href = '/result';
            } else {
                window.location.href = '/quiz?index=' + data.nextIndex;
            }
        }, 1000);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('通信エラーが発生しました。');
    });
});