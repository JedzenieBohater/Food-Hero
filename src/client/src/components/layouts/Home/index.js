import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'


const mapStateToProps = ({ languageReducer }) => ({
  lang: languageReducer.home
})

export const Home = (props) => {
  return (
	  <div>
    {/* <header>
		<a>
			<div className="logo">
				<img className="img-logo" src="static/images/Logo.png" />
			</div>
		</a>
		<div className="navbar">
			<a>
				<img className="img-searchLoop" src="static/images/searchLoop.png" />
			</a>
			<div className="btn-login-container">
				<button className="btn btn-login">Zaloguj</button>	
			</div>
		</div>
	</header> */}
	<div className="main">
		<div className="row">
	  		<div className="section">
	    		<div className="img-container">
	    			<a className="img-main-section">
						<img src="static/images/want-eat.jpeg" className="img-main-section" />
					</a>
					<a className="text-block"><div>
						Chcę jeść
					</div></a>
				</div>
				<div className="separator separator-horizontal"></div>
				<div className="img-container">
					<a className="img-main-section">
						<img src="static/images/want-cook.jpeg" className="img-main-section" />
					</a>
					<a className="text-block"><div>
						Chcę gotować
					</div></a>
				</div>
	  		</div>
	  		<div className="separator separator-vertical"></div>
	  		<div className="section">
	  			<div className="text-section">
	  				<div style={{ width: "100%", textAlign: "center" }}>
	  					<img src="static/images/Food Hero.png" />
	  				</div>
		    		<p className="quote">”Łączenie pasjonatów to nasza pasja”</p>
		    		<p style={{ textAlign: "center" }}>Czym jest Food Hero?</p>
		    		<p>Jest to pierwsza platforma łącząca miłośników jedzenia z&nbsp;miłośnikami gotowania w&nbsp;całym kraju.</p>
		    		<p>Zarejestruj się i&nbsp;stań się częścią naszej kulinarnej społeczności.</p>
		    		<p>Nie masz czasu, bądź gotowanie nie jest Twoją mocną stroną? Wybierz jedną z&nbsp;dostępnych ofert lub dodaj własne zamówienie, wybierz sposób płatności i&nbsp;dostawy, a&nbsp;następnie ciesz się domownym jedzeniem!</p>
		    		<p>Lubisz gotować i&nbsp;chciałbyś na tym zarobić? Spróbuj swoich sił jako kucharz. Dodawaj oferty lub wybieraj gotowe zgłoszenia, gotuj i&nbsp;zarabiaj na jedzeniu, a&nbsp;to wwszystko w&nbsp;domowym zaciszu!</p>
		    		<h3>Wypróbuj już teraz!</h3>
		    	</div>
	    		<div className="btn-container" style={{ margin: "auto" }}>
					<button className="btn btn-register">Zarejestruj</button>	
				</div>
	  		</div>
		</div>
	</div>
	</div>
  )
}


export default connect(mapStateToProps)(Home)