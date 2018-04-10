-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 23, 2017 at 04:19 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gudang_rental`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_inventaris_barang`
--

CREATE TABLE `t_inventaris_barang` (
  `kode_barang` varchar(5) NOT NULL,
  `nama_barang` varchar(35) NOT NULL,
  `jenis_barang` varchar(30) NOT NULL,
  `merek_barang` varchar(15) NOT NULL,
  `status_barang` varchar(10) NOT NULL,
  `jumlah` int(2) NOT NULL,
  `tahun_buat` varchar(4) NOT NULL,
  `kondisi_barang` varchar(15) NOT NULL,
  `harga` int(10) NOT NULL,
  `tgl_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_inventaris_barang`
--

INSERT INTO `t_inventaris_barang` (`kode_barang`, `nama_barang`, `jenis_barang`, `merek_barang`, `status_barang`, `jumlah`, `tahun_buat`, `kondisi_barang`, `harga`, `tgl_masuk`) VALUES
('B0001', 'BabyZen Yoyo', 'Stoller', 'BabyZen', 'Disewa', 3, '2014', 'Baik', 100000, '2017-04-08'),
('B0002', 'Medella Mini Electric', 'Breast Pump', 'Medella', 'Disewa', 2, '2014', 'Baik', 100000, '2014-04-23'),
('B0003', 'Boba 3G - Baby Carrier', 'Baby Carrier', 'Boba', 'Disewa', 2, '2014', 'Baik', 125000, '2017-04-22'),
('B0004', 'Maxi Cosi Cabriofix', 'Car Seat', 'Maxi Cosi', 'Disewa', 3, '2014', 'Baik', 175000, '2017-04-24'),
('B0005', 'Trunkisaurus Rex', 'Tas & Koper ', 'Trunki', 'Disewa', 5, '2014', 'Baik', 50000, '2017-04-24'),
('B0006', 'BabyZen Bay', 'Baby Box', 'BabyZen', 'Disewa', 4, '2014', 'Baik', 190000, '2017-04-05'),
('B0008', 'Baby Box Bayi Does', 'Baby Box', 'Does', 'Disewa', 4, '2015', 'Baik', 15000, '2017-06-01'),
('B0009', 'Cocolatte Bree+', 'Stoller', 'Bree+', 'Disewa', 3, '2006', 'Baik', 130000, '2017-06-14'),
('B0010', 'Bumbo Multi Seat + Toy', 'Baby Seats ', 'Bumbo', 'Disewa', 4, '2016', 'Baik', 120000, '2017-06-20');

-- --------------------------------------------------------

--
-- Table structure for table `t_login`
--

CREATE TABLE `t_login` (
  `user` varchar(12) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_login`
--

INSERT INTO `t_login` (`user`, `password`) VALUES
('admin1', 'achmad');

-- --------------------------------------------------------

--
-- Table structure for table `t_peminjam`
--

CREATE TABLE `t_peminjam` (
  `kode_peminjam` varchar(5) NOT NULL,
  `nama_peminjam` varchar(30) NOT NULL,
  `no_ktp` varchar(16) NOT NULL,
  `jenis_kelamin_peminjam` varchar(10) NOT NULL,
  `alamat_peminjam` text NOT NULL,
  `telepon_peminjam` varchar(12) NOT NULL,
  `kota` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_peminjam`
--

INSERT INTO `t_peminjam` (`kode_peminjam`, `nama_peminjam`, `no_ktp`, `jenis_kelamin_peminjam`, `alamat_peminjam`, `telepon_peminjam`, `kota`) VALUES
('C0001', 'Nurdin', '3275090812960006', 'Lelaki', 'Jl.Bromo Komplek Pemda ', '0218882291', 'Bekasi'),
('C0002', 'Pricillia', '4386101923071117', 'Perempuan', 'Jl.Dahlia No. 9', '0218211191', 'Bekasi'),
('C0003', 'Lucy', '4397212034182228', 'Perempuan', 'Jl. Rawa Lele No.13', '0219345567', 'Bekasi'),
('C0004', 'Lilis', '6508323145293339', 'Perempuan', 'Jl. Naga No.20', '0219345523', 'Jakarta '),
('C0005', 'Nina', '7619434256304440', 'Perempuan', 'Jl. Cut Nyak Dien', '0219453329', 'Bogor'),
('C0006', 'Luidji', '1205090709867003', 'Lelaki', 'Jl.Wibawa Mukti No.11 Jatiasih', '08998799630', 'Bekasi'),
('C0007', 'Ahlan Wa Sahlan', '1213244565788081', 'Lelaki', 'Jl.Anggrek No.3 Jati Melati', '087888792818', 'Bekasi'),
('C0008', 'Wenny Nikita ', '1224036009960010', 'Perempuan', 'Jl.Muhajirin  No.3 Jati Melati', '089635603577', 'Bekasi'),
('C0009', 'Sarah Yukino', '1234083937124086', 'Perempuan', 'Jln. Palem Raya Jatimurni', '081316954802', 'Bekasi'),
('C0010', 'Endang Dwi Wiji', '3502176908680002', 'Perempuan', 'Jl. Pahlawan 01', '0218897235', 'Jakarta Timur');

-- --------------------------------------------------------

--
-- Table structure for table `t_peminjaman`
--

CREATE TABLE `t_peminjaman` (
  `kode_peminjaman` varchar(5) NOT NULL,
  `kode_barang` varchar(5) NOT NULL,
  `kode_peminjam` varchar(5) NOT NULL,
  `jumlah_barang` int(2) NOT NULL,
  `tanggal_peminjaman` date NOT NULL,
  `tanggal_kembali` date NOT NULL,
  `kondisi_barang` varchar(15) NOT NULL,
  `total_bayar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_peminjaman`
--

INSERT INTO `t_peminjaman` (`kode_peminjaman`, `kode_barang`, `kode_peminjam`, `jumlah_barang`, `tanggal_peminjaman`, `tanggal_kembali`, `kondisi_barang`, `total_bayar`) VALUES
('P0001', 'B0001', 'C0002', 1, '2017-07-21', '2017-07-25', 'Baik', 27142),
('P0002', 'B0002', 'C0001', 1, '2017-07-21', '2017-07-24', 'Baik', 22857),
('P0003', 'B0004', 'C0001', 1, '2017-07-21', '2017-07-23', 'Baik', 25000),
('P0004', 'B0005', 'C0005', 1, '2017-07-19', '2017-07-23', 'Baik', 35000),
('P0005', 'B0007', 'C0009', 1, '2017-07-19', '2017-07-24', 'Baik', 32000),
('P0006', 'B0009', 'C0005', 1, '2017-07-19', '2017-07-25', 'Baik', 55000),
('P0007', 'B0007', 'C0009', 1, '2017-07-20', '2017-07-24', 'Baik', 20000),
('P0008', 'B0007', 'C0001', 1, '2017-07-20', '2017-07-25', 'Baik', 10000),
('P0009', 'B0003', 'C0005', 1, '2017-07-18', '2017-07-26', 'Baik', 40000),
('P0010', 'B0001', 'C0003', 4, '2017-07-17', '2017-07-24', 'Baik', 50000),
('P0011', 'B0002', 'C0004', 1, '2017-07-18', '2017-07-25', 'Baik', 60000),
('P0012', 'B0003', 'C0005', 1, '2017-07-19', '2017-07-26', 'Baik', 65000),
('P0013', 'B0010', 'C0010', 1, '2017-07-22', '2017-07-29', 'Baik', 60000);

-- --------------------------------------------------------

--
-- Table structure for table `t_pengembalian`
--

CREATE TABLE `t_pengembalian` (
  `kode_pengembalian` varchar(5) NOT NULL,
  `kode_peminjaman` varchar(5) NOT NULL,
  `total_harga` int(10) NOT NULL,
  `tanggal_pengembalian` date NOT NULL,
  `kondisi_barang` varchar(15) NOT NULL,
  `denda` int(10) NOT NULL,
  `total_akhir` int(10) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembali` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_pengembalian`
--

INSERT INTO `t_pengembalian` (`kode_pengembalian`, `kode_peminjaman`, `total_harga`, `tanggal_pengembalian`, `kondisi_barang`, `denda`, `total_akhir`, `bayar`, `kembali`) VALUES
('K0001', 'P0001', 100000, '2017-06-05', 'Baik', 17142, 117142, 120000, 2858),
('K0002', 'P0002', 35000, '2017-07-16', 'Baik', 15000, 50000, 50000, 0),
('K0003', 'P0006', 60000, '2017-07-16', 'Baik', 10000, 70000, 100000, 30000),
('K0004', 'P0008', 40000, '2017-07-18', 'Baik', 12000, 62000, 65000, 3000),
('K0005', 'P0002', 45000, '2017-07-18', 'Baik', 20000, 65000, 65000, 0),
('K0006', 'P0004', 23000, '2017-07-20', 'Rusak Ringan', 14000, 37000, 40000, 3000),
('K0007', 'P0006', 160000, '2017-07-20', 'Baik', 10000, 170000, 200000, 30000),
('K0009', 'P0005', 79000, '2017-07-21', 'Baik', 6500, 80500, 81000, 500),
('K0010', 'P0003', 25000, '2017-07-22', 'Baik', 0, 25000, 25000, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_inventaris_barang`
--
ALTER TABLE `t_inventaris_barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `t_peminjam`
--
ALTER TABLE `t_peminjam`
  ADD PRIMARY KEY (`kode_peminjam`);

--
-- Indexes for table `t_peminjaman`
--
ALTER TABLE `t_peminjaman`
  ADD PRIMARY KEY (`kode_peminjaman`),
  ADD KEY `kode_peminjam` (`kode_peminjam`),
  ADD KEY `kode_peminjam_2` (`kode_peminjam`);

--
-- Indexes for table `t_pengembalian`
--
ALTER TABLE `t_pengembalian`
  ADD PRIMARY KEY (`kode_pengembalian`),
  ADD KEY `kode_peminjaman` (`kode_peminjaman`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
