import React from 'react'
import List from './List'
import data from './exampledata.json'

const offers = data.offers;

export default () => {
  return (
    <div className="searchmain">
    <div className='content-box col25'>Wyszukiwarka</div>
    <div className='content-box col75'>
        {offers.map( offer => (<List
        id={offer.id}
        picture={offer.picture}
        title={offer.title} 
        wystawiajacy={offer.cook}
        data={offer.date}
        lokalizacja={offer.location}
        ocena={offer.grade}
        opis={offer.description}
        ></List>))}
    </div>
    </div>
  )
}