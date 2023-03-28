import "./WordsList.css";

export function WordsList( { topWordsCollection } ){

    const renderWords = () => {

        const sortedTopWordsCollection = Object.fromEntries(
            Object.entries(topWordsCollection)
            .sort(([,a],[,b]) => b-a)
        );

        return Object.entries(sortedTopWordsCollection).map( ([word, timeAppears], i) => {
            return(
                 <tr key = { i }>
                    <td>{word}</td>
                    <td>{timeAppears}</td>
                </tr>
            );
        })
    };

    return(
        <div>
             <table>
                <thead>
                    <tr>
                        <th>Word</th>
                        <th>Time Appears</th>
                    </tr>
                </thead>
                <tbody>
                    {renderWords()}
                </tbody>
            </table>
        </div>
    );
}